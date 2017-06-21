package controller;


import javax.servlet.ServletException;;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author  Davide Micarelli
 */
public class Logout extends BaseController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //chiudo la sessione
        sessionManager.destroySession(request);
        //reindirizzo alla home
        response.sendRedirect("Home");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
