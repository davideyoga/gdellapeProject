package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
import controller.utility.AccademicYear;
import controller.utility.AccademicYearException;
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
import java.util.Calendar;
import java.util.List;

/**
 * @authorDavide Micarelli
 * ModCourse e' la servlet per la modifica dei corsi da parte dei docenti, non gli e' permesso modificare varie impostazioni
 * DA QUESTA SERVLET NON E' PERMESSA LA MODIFICA DI UN CORSO SE DI UN ANNO PRECEDENTE
 *
 * NOTA IMPORTANTE: se l'utente e' un admin e un docente, non gli viene permesso l'accesso al corso a meno che non sia di sua proprieta',
 *                  nel caso si voglia modificare il corso da un admin usare ModAdmCourse
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
     */@Override
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

                //se il corso non esiste
                if(courseById == null || courseById.getIdCourse() <= 0 ){
                    response.sendRedirect("Error");
                    return;
                }

                //estraggo il servizio di modifica dei corsi
                Service modCourse = this.getServiceAndCreate(request, response, ds, "modCourse", "Service to modificate course associated with the user", datamodel, this.getServletContext());


                //controllo se l'utente e' associato a tale corso quindi puo' modificarlo
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modCourse) ||
                        courseListUser.contains(courseById)) {

                    AccademicYear attuale = new AccademicYear(Calendar.getInstance());

                    AccademicYear accademicYear = new AccademicYear(courseById.getYear());

                    //se il corso risulta essere di questo anno accademico (cosa buona)
                    if(attuale.equals(accademicYear)) {


                        //inserisco nella sessione dell'utente l'id del corso (per la futura richiesta post)
                        request.getSession().setAttribute("idCourseToModify", courseById.getIdCourse());

                        //lancio il template con il corso caricato
                        datamodel.put("course", courseById);

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //setto i servizi a cui ha accesso l'utente
                        this.datamodel.put("services", request.getSession().getAttribute("services"));

                        TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());

                        //se il corso non e' di questo anno accademico (il docente non puo' modificare un anno accademico che non sia quello attuale)
                    }else{

                        //lancio il messaggio di servizio non permesso
                        this.processNotPermitted(request, response);

                    }
                    //se l'utente non puo' modificare il corso
                } else {
                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }

                //chiudo il dao
                courseDao.destroy();

                //se non ha la sessione valida e troppo vecchia
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }
        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            this.processError(request, response);
        } catch (AccademicYearException e) {
            e.printStackTrace();

            this.processError(request, response);
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
                Course courseById = courseDao.getCourseById(Integer.parseInt(String.valueOf(request.getSession().getAttribute("idCourseToModify"))));

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());


                //controllo se l'utente puo' modificare tutti i corsi oppure l'utente e' associato a quel corso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse) ||
                        courseListUser.contains(courseById)) {

                    //se non ho l'id del corso da modificare in sessione
                    if(request.getSession().getAttribute("idCourseToModify") == null){

                        //lancio servlet di errore
                        response.sendRedirect("Error");

                        //se ho l'id del corso in sessione
                    }else {

                        //estraggo i dati dalla form
                        Course courseByForm = courseDao.getCourse();
                        courseByForm = this.getCourseByForm(request, courseByForm, courseById.getIdCourse());

                        //inserisco l'anno del corso prima al corso dopo, per far in modo che non vi sia la possibilita' di modificare l'anno del corso
                        courseByForm.setYear(courseById.getYear());
                        //stessa cosa per il codice del corso
                        courseByForm.setCode(courseById.getCode());

                        //siccome l'utente base (docente non puo' andare a modificare codice e nome non devo eseguire alcun controllo)

                        //se sono state effettuate modifiche al corso
                        if(!courseByForm.equals(courseById)) {

                            //setto l'id del corso
                            courseByForm.setIdCourse(courseById.getIdCourse());

                            //esegue l'update
                            courseDao.storeCourse(courseByForm);

                            //inserisco nel datamodel il corso
                            datamodel.put("course", courseByForm);

                            //inserisco il messaggio di avvenuta modifica
                            datamodel.put("message", "Course Modified");

                            //inserisco un log
                            logManager.addLog(sessionManager.getUser(request), "USER: " + sessionManager.getUser(request).toStringForLog() +
                                    " HAS CHANGED: " + courseById.toStringForLog(), ds);

                            //se non sono state effettuate modifiche
                        }else{

                            //carico il corso nel datamodel
                            datamodel.put("course", courseById);

                        }

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //setto i servizi a cui ha accesso l'utente
                        this.datamodel.put("services", request.getSession().getAttribute("services"));

                        //lancio il template
                        TemplateController.process("course_mod.ftl", datamodel,response,getServletContext());

                    }
                    //se l'utente non ha il permesso di modificare il corso
                }else{
                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }

                //se non ha sessione valida
            }else{
                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //setto i servizi a cui ha accesso l'utente
            this.datamodel.put("services", request.getSession().getAttribute("services"));

            //siccome ad un docente che non sia insrito il log non gle ne puo' fregare di meno lancio comunque il template
            TemplateController.process("course_mod.ftl", datamodel,response,getServletContext());
        }

    }

}
