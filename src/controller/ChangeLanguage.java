package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class ChangeLanguage extends BaseController {

    private Map <String, Object> datamodel = new HashMap <>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //cambio lingua, se non esiste la sessione la creo, se non e' settata la lingua la cambio in en
        sessionManager.changeLanguage(request);

        //reindirizzo alla servlet visitata precedentemente
        response.sendRedirect(sessionManager.getPreviusPage(request));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}