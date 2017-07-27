package controller;

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
 * @Creator Davide Micarelli
 */
public class ModCourse extends BaseController {


    /**
     * Controllo permessi, controllo se l'utente puo' modificare questo specifico corso,
     * raccolgo dati, controllo nome e codice,
     * eseguo update
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

                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //estraggo i corsi dell'utente
                List <Course> courseListUser = courseDao.getCoursesByUser(sessionManager.getUser(request));

                //estraggo il corso con id = al quello contenuto nella richiesta get
                Course courseById = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());


                //controllo se l'utente puo' modificare tutti i corsi oppure l'utente e' associato a quel corso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse) ||
                        courseListUser.contains(courseById)) {

                    //inserisco nella sessione dell'utente l'id del corso
                    request.getSession().setAttribute("courseToModify", courseById);

                    //lancio il template con il corso caricato
                    datamodel.put("course", courseById);
                    TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());

                    //se l'utente non puo' modificare il corso
                } else {
                    //lancio il messaggio di servizio non permesso
                    TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                }

                //chiudo il dao
                courseDao.destroy();

                //se non ha la sessione valida e troppo vecchia
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controllo i permessi
        try {

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //estraggo i corsi dell'utente
                List <Course> courseListUser = courseDao.getCoursesByUser(sessionManager.getUser(request));

                //estraggo il corso con id = al quello contenuto nella richiesta get
                Course courseById = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());


                //controllo se l'utente puo' modificare tutti i corsi oppure l'utente e' associato a quel corso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse) ||
                        courseListUser.contains(courseById)) {

                    Course courseBySession = (Course) request.getSession().getAttribute("courseToModify");

                    //se non ho l'id del corso da modificare in sessione
                    if(request.getSession().getAttribute("courseToModify") == null){

                        //lancio messaggio di errore
                        TemplateController.process("error.ftl", datamodel,response,getServletContext());

                        //se ho l'id del corso in sessione
                    }else {

                        //estraggo i dati dalla form
                        Course courseByForm = courseDao.getCourse();
                        courseByForm = this.getCourseByForm(request, courseByForm, courseBySession.getIdCourse());

                        //se l'utente ha cambiato nome e non esiste un nome uguale a quello inserito e
                        //(stessa cosa per codice) oppure
                        //l'utente non ha cambiato nome e codice
                        if(     !courseByForm.getName().equals(courseBySession.getName()) && courseDao.getCourseByName(courseByForm.getName()) == null ||
                                !courseByForm.getCode().equals(courseBySession.getCode()) && courseDao.getCourseByCode(courseByForm.getCode()) == null ||
                                courseByForm.getName().equals(courseBySession.getName()) && courseByForm.getCode().equals(courseBySession.getCode()))
                        {

                            //setto l'id del corso
                            courseByForm.setIdCourse(courseBySession.getIdCourse());

                            //esegue l'update
                            courseDao.storeCourse(courseByForm);

                            //inserisco nel datamodel il corso
                            datamodel.put("", courseByForm);

                            //inserisco il messaggio di avvenuta modifica
                            datamodel.put("message", "Course Modified");

                            //inserisco un log
                            logManager.addLog(sessionManager.getUser(request), "UPDATE COURSE: BEFORE: " + courseBySession +
                                                                                                        ". AFTER: " + courseBySession, ds);

                        }else{

                            if (courseDao.getCourseByName(courseByForm.getName()) != null){

                                //inserisco nel datamodel il messaggio di errore nome esistente nel db
                                datamodel.put("message", "Error: Existing Name ");
                            }

                            if(courseDao.getCourseByCode(courseByForm.getCode()) != null){

                                //concateno al messaggio di prima(se esiste) il messaggio di errore di codice gia' esistente
                                datamodel.put("message", datamodel.get("message") + "Error: Existing Code." );
                            }

                        }

                        //lancio il template
                        TemplateController.process("course_mod.ftl", datamodel,response,getServletContext());

                    }
                    //se l'utente non ha il permesso di modificare il corso
                }else{
                    //lancio il messaggio di servizio non permesso
                    TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                }

                //se non ha sessione valida
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());

        } catch (LogException e) {
            e.printStackTrace();

            //inserisco messaggio di avvenuto modifica ma errore di lo
        }

    }
}
