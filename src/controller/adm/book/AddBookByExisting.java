package controller.adm.book;

import controller.BaseController;
import controller.utility.SecurityLayer;
import dao.exception.DaoException;
import dao.implementation.BookDaoImpl;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.BookDao;
import dao.interfaces.CourseDao;
import model.Book;
import model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 * aggiunge libro con ajax
 */
public class AddBookByExisting extends BaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        System.out.println("gianni");


        //controllo l'esistenza del parametro idBook e idCourse
        if(request.getParameter("idBook")!= null && request.getParameter("idCourse")!= null ){

            try {

                //inizializzo i dao
                BookDao bookDao= new BookDaoImpl(ds);
                bookDao.init();

                CourseDao courseDao = new CourseDaoImpl(ds);
                courseDao.init();

                //casto l'id del libro e del corso
                int idBook = SecurityLayer.checkNumeric(request.getParameter("idBook"));
                int idCourse = SecurityLayer.checkNumeric(request.getParameter("idCourse"));
                System.out.println("idBook: " + idBook + " \nidcourse: " + idCourse);

                //estraggo il libro e il corso
                Book book= bookDao.getBookById(idBook);
                Course course = courseDao.getCourseById(idCourse);

                //se esiste sia il libro che il corso
                if(book != null && course != null){

                    //aggiungo al il libro al corso
                    bookDao.addLinkBookToCourse(course, book);

                    //torno OK alla chiamata servlet se arrivo alla fine senza problemi
                    response.getWriter().write("OK");

                }else{

                    //torno KO alla chiamata servlet se ho avuto problemi
                    response.getWriter().write("KO");
                }


                //chiudo i dao
                bookDao.destroy();
                courseDao.destroy();


            } catch (DaoException | NumberFormatException e) {
                e.printStackTrace();

                //torno KO alla chiamata servlet se ho avuto problemi
                response.getWriter().write("KO");
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }


}
