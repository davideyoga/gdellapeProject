package controller;

import controller.utility.AccademicYear;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.StudyCourseDao;
import model.Course;
import view.TemplateController;
import model.StudyCourse;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class StudyCourseProfile extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Estrae da richiesta GET il codice, estrae il corso di studi con tale codice e lo restituisce a video
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            //inizializzo il dao del corso di studi
            StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
            studyCourseDao.init();
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            //se nei parametri GET non e' presente il codice lancio la lista dei corsi di studio
            if(request.getParameter("code") == null || request.getParameter("code").equals("")){

                response.sendRedirect("ListStudyCourse");
                return;
            }

            //estraggo il corso di studi dal codice inserito nei parametri get
            StudyCourse studyCourse = studyCourseDao.getStudyCourseByCode(request.getParameter("code"));

            //creo un AccademicYear
            AccademicYear accademicYear;

            //controllo la presenza del parametro get che rappresenta l'anno
            //se non presente
            if(request.getParameter("age") == null || request.getParameter("age").equals("")){

                //se non esiste setto l'anno accademico a quello attuale
                accademicYear = new AccademicYear(Calendar.getInstance());

                //se presente
            }else{

                //inizializzo l'anno accademico
                accademicYear = new AccademicYear(Integer.parseInt(request.getParameter("age")));

            }


            //estraggo la lista dei corsi dell'anno richiesto appartenente al corso di studi
            List<Course> courses = courseDao.getCourseByStudyCourseAndYear(studyCourse ,accademicYear.toString());

            //se il corso di studi estratto esiste
            if(studyCourse != null && studyCourse.getId() > 0) {


                //inserisco i dati nel datamodel
                datamodel.put("studyCourse", studyCourse);
                datamodel.put("courses", courses);
                datamodel.put("currentFirstYear", accademicYear.getFirstYear());
                datamodel.put("accademicYear", accademicYear.toString());

                //carico la lingua nel datamodel
                this.setLng(request, datamodel);

                //setto l'utente in sessione
                this.datamodel.put("user", sessionManager.getUser(request));

                processTemplate(request, response, "study_course_profile", datamodel);

            }else{

                //lancio metodo di errore
                this.processError(request, response);
            }

            //chiudo il dao
            studyCourseDao.destroy();
            courseDao.destroy();

        } catch (DaoException | NumberFormatException e) {
            //lancio template di errore
            TemplateController.process("error.ftl", datamodel, response, getServletContext());
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
