package controller;

import dao.exception.DaoException;
import dao.implementation.CourseDaoImpl;
import dao.interfaces.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 */
public class ListCourseAllAn extends BaseController {


    protected void processRequest( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {

            CourseDao courseDao = new CourseDaoImpl(ds);
            courseDao.init();





        }catch (NumberFormatException | DaoException e){
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
