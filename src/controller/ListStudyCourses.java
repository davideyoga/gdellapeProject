package controller;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.StudyCourseDao;
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
 * @author Davide Micarelli
 */
public class ListStudyCourses extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {

            //inizializzo il dao
            StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
            studyCourseDao.init();

            //estraggo tutti i corsi di studio
            List<StudyCourse> studyCourses = studyCourseDao.getAllStudyCourses();

            //chiudo il dao
            studyCourseDao.destroy();

            //inserisco la lista dei corsi nel datamodel del template
            datamodel.put("studyCourses", studyCourses);

            //carico la lingua nel datamodel
            this.setLng(request, datamodel);

            //carico nome servlet
            this.datamodel.put("nameServlet","ListStudyCourses");


            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //lancio il template
            processTemplate(request, response, "study_course_list", datamodel);



        } catch (DaoException e) {
            //lancio messaggio di errore
            System.out.println(e.getMessage());
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
