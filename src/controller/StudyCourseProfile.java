package controller;

import dao.exception.DaoException;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.StudyCourseDao;
import view.TemplateController;
import model.StudyCourse;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
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

            StudyCourse studyCourse = studyCourseDao.getStudyCourseByCode(request.getParameter("code"));

            if(studyCourse != null && studyCourse.getId() > 0) {

                datamodel.put("studyCourse", studyCourse);

                //carico la lingua nel datamodel
                this.setLng(request, datamodel);

                processTemplate(request, response, "study_course_profile", datamodel);

            }else{
                //lancio template di errore
                TemplateController.process("error.ftl", datamodel, response, getServletContext());
            }

            studyCourseDao.destroy();

        } catch (DaoException e) {
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
