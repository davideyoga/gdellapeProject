package controller;

import dao.exception.DaoException;
import dao.implementation.BookDaoImpl;
import dao.implementation.CourseDaoImpl;
import dao.implementation.StudyCourseDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.BookDao;
import dao.interfaces.CourseDao;
import dao.interfaces.StudyCourseDao;
import dao.interfaces.UserDao;
import model.Book;
import model.Course;
import model.StudyCourse;
import model.User;
import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Davide Micarelli
 * Estrae il corso da chiamata get tramite l'id passato,
 * estrae i corsi borrowed ecc..
 * estrae docenti,
 * estrae corsi di studio a cui e' collegato
 *
 *  ("course",course);
 *  ("listStudyCourse",listStudyCourse);
    ("listBorrowed",listBorrowed); MUTUATO
    ("listModulated",listModulated); MODULATO
    ("listPreparatory",listPreparatory); PROPEDEUTICO
    ("listDocent",listDocent);
 */
public class CourseProfile extends BaseController {

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        datamodel.put("user", sessionManager.getUser(request));

        //lancio template appropriato
        processTemplate(request, response, "course_profile", datamodel);

    }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            //estraggo il corso tramite l'id
            if(request.getParameter("id")!=null){

                //inizilizzo i dao
                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                Course course = courseDao.getCourseById(Integer.parseInt(request.getParameter("id")));

                if(course==null || course.getIdCourse() == 0){

                    courseDao.destroy();
                    this.processError(request, response);

                    //se va tutto bene
                }else{

                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    StudyCourseDao studyCourseDao = new StudyCourseDaoImpl(ds);
                    studyCourseDao.init();

                    BookDao bookDao = new BookDaoImpl(ds);
                    bookDao.init();


                    //estraggo i corsi mutuati ecc..
                    List<Course> listBorrowed = courseDao.getCourseBorrowed(course);
                    List<Course> listModulated = courseDao.getCourseModulated(course);
                    List<Course> listPreparatory = courseDao.getCoursePreparatory(course);

                    //estraggo i corsi precedenti e successivi del corso
                    List<Course> courseRelatedByYear = courseDao.getCoursesByCode(course.getCode());

                    //estraggo i docenti che possiedono il corso
                    List<User> listDocent = userDao.getUserByCourse(course);

                    //estraggo i corsi di studio in cui il corso e' fruibile
                    List<StudyCourse> listStudyCourse = studyCourseDao.getStudyCourseByCourse(course);

                    //estraggo i libri
                    List<Book> listBook = bookDao.getBookByCourse(course);

                    //inserisco tutto nel datamodel
                    datamodel.put("course",course);
                    datamodel.put("listStudyCourse",listStudyCourse);
                    datamodel.put("listBorrowed",listBorrowed);
                    datamodel.put("listModulated",listModulated);
                    datamodel.put("listPreparatory",listPreparatory);
                    datamodel.put("courseRelatedByYear",courseRelatedByYear);
                    datamodel.put("listBook", listBook);






                    datamodel.put("value", "CourseProfile");





                    utilityManager.removePassword(listDocent);

                    datamodel.put("listDocent",listDocent);

                    System.out.println("\n");
                    System.out.println("\n");
                    System.out.println("listBook: "+ listBook);
                    System.out.println("\n");
                    System.out.println("\n");

                    //chiudo i dao
                    userDao.destroy();
                    studyCourseDao.destroy();
                    courseDao.destroy();
                    bookDao.destroy();

                    //lancio il processo template
                    this.processTemplate(request, response);
                }


            }else{
                this.processError(request, response);
            }

        } catch (DaoException | NumberFormatException e) {

            e.printStackTrace();

            this.processError(request, response);
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
