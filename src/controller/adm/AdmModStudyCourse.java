package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.interfaces.CourseDao;
import dao.interfaces.StudyCourseDao;
import model.Course;
import model.Service;
import model.StudyCourse;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class AdmModStudyCourse extends BaseController{

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    private void inserCourse(StudyCourse studyCourse){

        try {

            //inizializzo i corsi
            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();

            //estraggo i corsi che vengono erogati dal mio corso di studi
            List<Course> corsiDelCorsoDiStudi = courseDao.getCourseByStudyCourse(studyCourse);

            //estraggo tutti i corsi di studio
            List<Course> corsiDiStudio = courseDao.getCourses();

            //inserisco nel datamodel i corsi
            datamodel.put("listCourseByStudyCourse",corsiDelCorsoDiStudi);
            datamodel.put("listCourses", corsiDiStudio);


        } catch (DaoException e) {
            e.printStackTrace();
        }


    }

    /**
     * Dopo i vari controlli estrae dalla chiamata get l'id del corso di studi, lo estrae e lo inserisce nel datamodel
     * in fine si lancia il template
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se la sessione e' valida
            if(sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = utilityManager.getServiceAndCreate(request, response, ds, "modStudyCourse", "Permissed for modification Study Course",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                    //inizializzo il dao
                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    //estraggo il corso di studi dall'id passato
                    StudyCourse studyCourse = studyCourseDao.getStudyCourseById(Integer.parseInt(request.getParameter("id")));

                    if(studyCourse!= null && studyCourse.getId() > 0){

                        //inserisco nel template il corso di studi
                        datamodel.put("studyCourse", studyCourse);

                        //inserisco tutti i corsi e i corsi associati al corso di studi nel datamodel
                        inserCourse(studyCourse);

                        //lancio il template
                        TemplateController.process("study_course_mod_adm.ftl", datamodel,response,getServletContext());

                        //se il corso di studi estratto non esiste
                    }else{
                        //lancio messaggio di errore
                        TemplateController.process("error.ftl", datamodel,response,getServletContext());
                    }


                    //infine chiudo il dao
                    studyCourseDao.destroy();

                    //se non ha il permesso
                }else{
                    //lancio il template di non permesso
                    TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
                }

                //se la sessione non valida
            }else{
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request,response,"AdmModStudyCourse");
            }

        } catch (DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
