package controller.adm.studyCourse;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.StudyCourseDao;
import model.Service;
import model.StudyCourse;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */
public class AdmGetListStudyCourse extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se la sessione e' valida
            if(sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service viewUser = this.getServiceAndCreate(request, response, ds, "viewStudyCourse", "Permitted for view Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(viewUser)) {

                    //inizializzo il dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //estraggo tutti i corsi di studio
                    List<StudyCourse> studyCourses = studyCourseDao.getAllStudyCourses();

                    //chiudo il dao
                    studyCourseDao.destroy();

                    //inserisco nel datamodel tutti i corsi di studio
                    datamodel.put("studyCourses", studyCourses);

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template
                    TemplateController.process("study_course_list_adm.ftl", datamodel,response,getServletContext());

                    //se l'utente non ha il permesso
                }else{
                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }

                //se l'utente non ha una sessione valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmGetListStudyCourse");
            }
        } catch (DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
        }

    }

    /**
     * Estraggo dal db tutti i corsi di studio, li inserisco nel datamodel e lancio il template
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
