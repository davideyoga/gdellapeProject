package controller.adm;

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

                    new AccademicYear(Calendar.getInstance()).getFirstYear();


                    //se il corso risulta essere di questo anno accademico (cosa buona)
                    if( (new AccademicYear(Calendar.getInstance())).equals(new AccademicYear(courseById.getYear()))) {


                        //inserisco nella sessione dell'utente l'id del corso (per la futura richiesta post)
                        request.getSession().setAttribute("courseToModify", courseById);

                        //lancio il template con il corso caricato
                        datamodel.put("course", courseById);
                        TemplateController.process("course_mod.ftl", datamodel, response, getServletContext());

                        //se il corso non e' di questo anno accademico (il docente non puo' modificare un anno accademico che non sia quello attuale)
                    }else{

                        //lancio il messaggio di servizio non permesso
                        TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());

                    }
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
        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            TemplateController.process("error.ftl", datamodel, response, getServletContext());
        } catch (AccademicYearException e) {
            e.printStackTrace();

            TemplateController.process("error.ftl", datamodel, response, getServletContext());
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

                    //se non ho l'id del corso da modificare in sessione
                    if(request.getSession().getAttribute("courseToModify") == null){

                        //lancio servlet di errore
                        response.sendRedirect("Error");

                        //se ho l'id del corso in sessione
                    }else {

                        //estraggo i dati dalla form
                        Course courseByForm = courseDao.getCourse();
                        courseByForm = this.getCourseByForm(request, courseByForm, courseById.getIdCourse());

                        //inserisco l'anno del corso prima al corso dopo, per far in modo che non vi sia la possibilita' di modificare l'anno del corso
                        courseByForm.setYear(courseById.getYear());

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
                            logManager.addLog(sessionManager.getUser(request), "UPDATE COURSE: BEFORE: " + courseById +
                                    ". AFTER: " + courseByForm, ds);

                            //se non sono state effettuate modifiche
                        }else{

                            //carico il corso nel datamodel
                            datamodel.put("course", courseById);

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

            //siccome ad un docente che non sia insrito il log non gle ne puo' fregare di meno lancio comunque il template
            TemplateController.process("course_mod.ftl", datamodel,response,getServletContext());
        }

    }

}