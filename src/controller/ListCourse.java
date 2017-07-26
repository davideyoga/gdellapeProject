package controller;

import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
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
public class ListCourse extends BaseController {

    /**
     * Controlla permessi, inserisce la lista dei corsi che l'utente puo' modificare e lancia il template
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //elimino messaggio
            datamodel.put("message", null);

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service modAllCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());

                //estraggo il servizio di creazione dei gruppi
                Service modCourse = this.getServiceAndCreate(request, response, ds, "modAllCourse", "Service to modificate all course", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modAllCourse)) {

                    //estraggo tutti i corsi

                    //inizializzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //inserisco i corsi nel datamodel
                    datamodel.put("courses", courseDao.getCourses());

                    courseDao.destroy();

                    //lancio il template dei corsi dell'admin che puo' anche eliminarli
                    TemplateController.process("list_courses_adm.ftl", datamodel, response, getServletContext());

                    //se non ha il permesso di modificare tutti i corsi
                } else {

                    //se ha il permesso di modificare alcuni corsi ( quindi e' un docente)
                    if (((List <Service>) request.getSession().getAttribute("services")).contains(modCourse)) {

                        //inizializzo il dao dei corsi
                        CourseDao courseDao = new CourseDaoImpl(ds);
                        courseDao.init();

                        //estraggo i corsi collegati all'utente in sessione e li inserisco i corsi nel datamodel
                        datamodel.put("courses", courseDao.getCoursesByUser(sessionManager.getUser(request)));

                        courseDao.destroy();

                        //lancio il template dell'utente che non puo' eliminare i corsi
                        TemplateController.process("list_courses.ftl", datamodel, response, getServletContext());

                        //se non ha nessuno dei due permessi
                   } else {
                        //lancio il messaggio di servizio non permesso
                        TemplateController.process("not_permissed.ftl", datamodel, response, getServletContext());
                    }
                }
                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");

            }

        } catch (DaoException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

}
