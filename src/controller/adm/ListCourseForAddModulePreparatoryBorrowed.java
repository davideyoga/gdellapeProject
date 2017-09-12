package controller.adm;

import controller.BaseController;
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
 * Passato tramite GET l'id del corso,
 * si estraggono tutti i corsi e quelli che sono mutuati, moduli o propedeutici del miop corso.
 */
public class ListCourseForAddModulePreparatoryBorrowed extends BaseController{

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //controlli su sessione e permessi
        try {
            //pulisco messaggio
            datamodel.put("message", null);

            //se sessione valida
            if (this.sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modAdmCourse = this.getServiceAndCreate(request, response, ds, "modAdmCourse", "Permission for modification all course",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modGroups...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAdmCourse)) {


                    //controllo la presenza dell'id del corso come parametro GET
                    if(request.getParameter("idCourse") != null){

                        //inizializzo il dao dei corsi
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();

                        //estraggo il corso con tale id
                        Course courseWithId = courseDao.getCourseById(Integer.parseInt(request.getParameter("idCourse")));

                        //controllo l'esistenza del corso con tal id
                        if(courseWithId != null ){

                            //estraggo i corsi mutuati, prop, ecc... del mio corso
                            List coursesModulated = courseDao.getCourseModulated(courseWithId);
                            List coursesPropedeutic = courseDao.getCoursePropedeutic(courseWithId);
                            List coursesBorrowed = courseDao.getCourseBorrowed(courseWithId);

                            //estraggo tutti i corsi
                            List allCourse = courseDao.getCourses();

                            //inserisco il corso nel datamodel
                            datamodel.put("course", courseWithId);

                            //inserisco nel datamodel i corsi prop, modulati ecc...
                            datamodel.put("coursesModulated", coursesModulated);
                            datamodel.put("coursesPropedeutic", coursesPropedeutic);
                            datamodel.put("coursesBorrowed", coursesBorrowed);

                            //chiudo il dao
                            courseDao.destroy();

                            //lancio il template
                            TemplateController.process("listCourseForAddingModuleBorrowedPreparatory.ftl", datamodel,response,getServletContext());

                            //se non esiste corso con tale id
                        }else{

                            //chiudo il dao
                            courseDao.destroy();

                            //reindirizzo alla lista dei corsi
                            response.sendRedirect("ListCourse");
                        }


                        //se non e' presente il parametro get dell'id del corso
                    }else{

                        //reindirizzo alla lista dei corsi
                        response.sendRedirect("ListCourse");
                    }


                    //se non ho il permesso
                } else {
                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }

                //se sessione non valida
            } else {
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        }catch (NumberFormatException e){

            //se l'id non e' un intero lancio un messaggio di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }
}
