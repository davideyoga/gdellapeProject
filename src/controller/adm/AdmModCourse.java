package controller.adm;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Course;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 *
 * Servlet per la modifica di un corso da parte degli admin, il corso puo' essere modificato nella
 * sua interezza
 */
public class AdmModCourse extends BaseController {

    /**
     * Controlli sull'accessibilita' dell'user,
     * estrazione del corso a seconda dell;id nel paramtro get,
     * inserimento nel datamodel del corso e lancio template
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //controllo se la richiesta contiene il parametro get
                if (request.getParameter("id") == null) {
                    response.sendRedirect("ListCourse");
                    return;
                }

                //estraggo il servizio di creazione degli utenti
                Service modAdmCourse = this.getServiceAndCreate(request, response, ds, "modAdmCourse", "Permission for modification all course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modAdmCourse...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAdmCourse)) {

                    //inizializzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo il corso con id = al quello contenuto nella richiesta get
                    Course courseById = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                    //chiudo il dao
                    courseDao.destroy();

                    //se il corso non esiste
                    if (courseById == null || courseById.getIdCourse() <= 0) {
                        response.sendRedirect("Error");
                        return;
                    }

                    //inserisco nella sessione dell'utente l'id del corso (per la futura richiesta post)
                    request.getSession().setAttribute("courseToModify", courseById);

                    //lancio il template con il corso caricato
                    datamodel.put("course", courseById);
                    TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());

                    //se l'utente non ha il permesso per accedere a tale servizio
                } else {

                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }
                //se non ha la sessione valida e troppo vecchia
            } else {
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            TemplateController.process("error.ftl", datamodel, response, getServletContext());
        }
    }

    /**
     * Eseguo i controlli,
     * raccolgo i dati dalla form e controllo se sono stati modificati,
     * Se si eseguo un update.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //controllo se la richiesta contiene il parametro get
                if (request.getParameter("id") == null) {
                    response.sendRedirect("ListCourse");
                    return;
                }

                //estraggo il servizio di creazione degli utenti
                Service modAdmCourse = this.getServiceAndCreate(request, response, ds, "modAdmCourse", "Permission for modification all course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAdmCourse)) {

                    //inizializzo un dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //se esiste in sessione il corso da modificare
                    if( request.getSession().getAttribute("courseToModify") != null){

                        //estraggo il corso prima della modifica dall'id in sessione
                        Course courseBefore = courseDao.getCourseById((Integer) request.getSession().getAttribute("courseToModify"));

                        //estraggo il corso dalla form
                        Course courseAfter = courseDao.getCourse();
                        courseAfter = this.getCourseByForm(request, courseAfter, courseBefore.getIdCourse());

                        //inserisco l'anno del corso prima al corso dopo, per far in modo che non vi sia la possibilita' di modificare l'anno del corso
                        courseAfter.setYear(courseBefore.getYear());

                        //se il corso estratto dal db esiste
                        if( courseBefore != null) {


                            //se sono diversi eseguo modifiche
                            if (!courseAfter.equals(courseBefore)) {

                                //eseguo update
                                courseDao.storeCourse(courseBefore);

                                //inserisco il nuovo corso nel datamodel
                                datamodel.put("course", courseAfter);

                                //inserisco un log
                                logManager.addLog(sessionManager.getUser(request), "COURSE: " + courseBefore+ " IT'S CHANGE: " + courseAfter, ds);

                                //se i corsi sono uguali
                            } else {

                                //inserisco il corso nel datamodel
                                datamodel.put("course", courseBefore);

                            }

                            //lancio il template
                            TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());

                            //se il corso estratto dal db con id in sessione e' null
                        }else{

                            //lancio servlet di errore interno
                            response.sendRedirect("Error");

                        }

                        //se non e' presente in sessione l'id del corso da modificare
                    }else{

                        //lancio servlet di errore interno
                        response.sendRedirect("Error");
                    }

                    //se utente non ha il permesso
                } else {

                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }

                //se sessione non valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListCourse");

            }
        }catch (DaoException e){

            e.printStackTrace();

            TemplateController.process("error.ftl", datamodel, response, getServletContext());

        } catch (LogException e) {
            e.printStackTrace();

            //lancio il template
            TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());
        }
    }
}