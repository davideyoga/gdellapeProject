package controller;

import controller.sessionController.SessionManager;
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
 */
public class AddMaterial extends BaseController {

    private void processTemplate(HttpServletRequest request, HttpServletResponse response, Course courseById) throws ServletException, IOException{

        //lancio il template con il corso caricato
        datamodel.put("course", courseById);
        TemplateController.process("add_material.ftl", datamodel, response, getServletContext());

    }

    /**
     * Metodo chiamato nel momento in cui non si possiedono i permessi per una tale operazione
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void notPermissed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //lancio la servlet di non permesso
        response.sendRedirect("Error");

    }


    /**
     * Prendo il parametro GET dell'id del corso,
     * controllo se possiede il permesso di aggiungere tutti i corsi oppure se puo' modificare questo specifico corso,
     * se si lancio il template di aggiunta del materiale
     *
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

                //estraggo il servizio di aggiunta del materiale ad un solo corso
                Service addMaterial = this.getServiceAndCreate(request, response, ds, "addMaterial", "Permission to add material to a course",
                        datamodel, getServletContext());

                //estraggo il servizio di aggiunta del materiale per tutti i corsi
                Service addMaterialAllCourse = this.getServiceAndCreate(request, response, ds, "addMaterialAllCourse", "Permission to add material in all course",
                        datamodel, getServletContext());


                //inizializzo il dao dei corsi
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                Course courseById = courseDao.getCourse();

                //estraggo l'id del corso contenuto nei parametri get e ne estraggo il corso controllando che esista
                if(request.getParameter("idCourse") != null && ( courseById = courseDao.getCourseById(SecurityLayer.checkNumeric(request.getParameter("idCourse"))) ) != null) {


                    /*
                        ADMIN
                     */

                    //se l'utente in sessione possiede il servizio addMaterialAllCourse, quindi puo' modificare ogni corso
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(addMaterialAllCourse)) {

                        this.processTemplate(request, response, courseById);


                        /*
                            DOCENTE
                         */

                        //se non ha il permesso di modificare tutti i corsi controllo se questo corso gli appartiene
                    } else {

                        //estraggo tutti i corsi appartenenti all'utente
                        List <Course> coursesByUser = courseDao.getCoursesByUser(sessionManager.getUser(request));

                        //controllo se il corso con id passato e' tra i corsi dell'utente
                        if (coursesByUser.contains(courseById)) {

                            //se si lancio il template
                            this.processTemplate(request, response, courseById);

                            //se il corso non e' tra i corsi dell'utente
                        }else{

                            //metodo per non permesso
                            this.notPermissed(request, response);

                        }

                    }

                    //se non e' presente nel parametro get l'id del corso oppure non esista un corso con tale id
                }else {

                    //lancio servlet di errore
                    this.processError(request, response);

                }

                //se non si possiede una sessione valida
            }else{

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException e) {
            e.printStackTrace();

            //lancio servlet di errore
            this.processError(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
