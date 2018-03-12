package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
import controller.utility.AccademicYear;
import controller.utility.SecurityLayer;
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
 * Unico modo per aggiornare un corso da un anno precedente,
 * dato un corso con id passato tramite get, se il corso non e' di questo anno,
 * viene creato copiando tutti i suoi dati tranne l'anno e l'id,
 *
 */
public class UpdateCourse extends BaseController {

    private void processTemplate(HttpServletRequest request, HttpServletResponse response, Course courseById){

        //carico il corso nel datamodel
        datamodel.put("course", courseById);

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio il template
        TemplateController.process("update_course.ftl", datamodel, response, getServletContext());

    }


    /**
     * Controlli su validita' della sessione e permesso,
     * prendo l'id del corso passato come parametro get,
     * estatto tale corso controllo se esiste e lo lancio nel template.
     * prendo il corso con tale id, e lo carico nel datamodel
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di aggiornare un corso
                Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio updateCourse...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {

                    //inizializzo un dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo id del corso, se il parametro e' null o non e' un numero si genera un NumberFormatException
                    int idCourse = SecurityLayer.checkNumeric(request.getParameter("idCourse"));

                    //estraggo il corso dall'id
                    Course courseById = courseDao.getCourseById(idCourse);

                    //se il corso esiste
                    if(courseById != null){


                        //chiudo il dao
                        courseDao.destroy();

                        //setto in sessione l'id del corso
                        request.getSession().setAttribute("idCourse", idCourse);

                        //lancio il template
                        this.processTemplate(request, response, courseById);

                        //se il corso con tale id non esiste
                    }else{

                        //chiudo il dao
                        courseDao.destroy();

                        //se il corso con id passato non esiste lancio la servlet di errore
                        this.processError(request, response);
                        return;
                    }


                    //se l'utente non possiede il servizio
                } else {

                    //lancio la servlet di non permesso\
                    this.processNotPermitted(request, response);

                }

                //se l'utente non ha una sessione valida
            } else {

                //lancio il login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            //lancio servlet di errore
            this.processError(request, response);
        }

    }

    /**
     * Controllo la presenza in sessione dell'id del corso, dalla form passata estraggo l'anno
     * del corso che si intende aggiornare, controllo che non vi sia un corso con stesso codice ed anno (quindi e' gia stato aggiornato)
     * se non esiste lo aggiorno copiando i dati vecchi nel nuovo corso tranne id e codice
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di aggiornare un corso
                Service updateCourse = this.getServiceAndCreate(request, response, ds, "updateCourse", "Permissed for update Course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio updateCourse...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(updateCourse)) {

                    //inizializzo un dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //estraggo id nella sessione del corso che si intende aggiornare, se non esiste o non e' un numero lancia un NumberFormatException
                    int idCourse = SecurityLayer.checkNumeric(String.valueOf(request.getSession().getAttribute("idCourse")));


                    //estraggo il corso (non viene messo tutto in sessione per maggiore sicurezza che esiste ancora)
                    Course courseById = courseDao.getCourseById(idCourse);

                    //se il corso esiste
                    if (courseById != null){

                        //estraggo l'anno dalla form
                        int firstYear = SecurityLayer.checkNumeric(request.getParameter("year"));
                        //inizializzo un anno accademico con l'anno estratto dalla form
                        AccademicYear accademicYear = new AccademicYear(firstYear);

                        //controllo sull'anno accademico: deve essere >= dell'anno accademico attuale, non devono esistere corsi con stesso codice e anno
                        if(courseDao.getCoursesByCodeAndYear(courseById.getCode(), accademicYear) == null){

                            //setto il nuovo corso per essere inserito nel database
                            courseById.setIdCourse(0);
                            courseById.setYear(accademicYear.toString());

                            //inserisco il corso
                            courseDao.storeCourse(courseById);

                            //chiudo il dao
                            courseDao.destroy();

                            //aggiungo log Update del corso
                            logManager.addLog(sessionManager.getUser(request), "USER:" + sessionManager.getUser(request).toStringForLog() +  " HAS UPDATED A COURSE: " + courseById.toStringForLog() , ds);

                            //lancio la servlet della lista dei corsi
                            response.sendRedirect("ListCourse");
                            return;


                            //se esistono altri corsi con stesso anno accademico e codice
                        }else{

                            //inserisco nel datamodel il messaggio di corso gia' aggiornato
                            datamodel.put("message", "ALREADY EXISTING COURSE");

                            //chiudo il dao
                            courseDao.destroy();

                            //lancio il template
                            this.processTemplate(request, response, courseById);

                        }



                        //se il corso non esiste
                    }else{

                        courseDao.destroy();

                        this.processError(request, response);

                    }

                    //se non possiede il permesso
                }else{

                    this.processNotPermitted(request, response);

                }

                //se non ha una sessione valida
            }else{
                //lancio il login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }


        }catch (NumberFormatException | DaoException e){
            e.printStackTrace();

            this.processError(request, response);

            this.processError(request, response);
        } catch (LogException e) {
            e.printStackTrace();

            //lancio la servlet della lista dei corsi
            response.sendRedirect("ListCourse");
        }
    }
}
