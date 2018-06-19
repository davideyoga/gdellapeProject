package controller.adm.course;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;
import model.Course;
import model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Creator Davide Micarelli
 */
public class DeleteCourse extends BaseController {

    /**
     * Controllo permessi, estrae da richiesta get il pamaetri id con il corso da eliminare, lancia servlet ListCourse
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //elimino messaggio
            datamodel.put("message", null);

            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di eliminazione del corso
                Service deleteCourse = this.getServiceAndCreate(request, response, ds, "deleteCourse", "Service to delete course", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio che gli permette di modificare tutti i corsi (quindi e' un amministratore)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(deleteCourse)) {

                    //inizializzo il dao dei corsi
                    CourseDao courseDao = new CourseDaoImpl(ds);
                    courseDao.init();

                    //se non e' presente il parametro get id
                    if(request.getParameter("id") == null){
                        //lancio la servlet della lista dei corsi
                        response.sendRedirect("ListCourse");
                        return;
                    }

                    Course course = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                    //elimino il corso con id = a quello del parametro get passato
                    courseDao.deleteCourse(course);

                    //aggiungo un log di eliminazione del corso
                    logManager.addLog(sessionManager.getUser(request),"USER: " + sessionManager.getUser(request).toStringForLog() + " DELETE COURSE: " + course.toStringForLog(), ds);

                    courseDao.destroy();

                    //lancio la servlet della lista dei corsi
                    response.sendRedirect("ListCourse");

                    //se non ha i permessi
                } else {
                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }
            }else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "ListCourse");
            }

        } catch (DaoException | NumberFormatException e) {
            e.printStackTrace();

            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {
            e.printStackTrace();

            //inserisco nel datamodel l'errore del log
            datamodel.put("message", "Course stored but error log" );

            //lancio la servlet della lista dei corsi
            response.sendRedirect("ListCourse");

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
