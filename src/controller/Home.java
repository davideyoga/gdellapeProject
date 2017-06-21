package controller;

import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */
public class Home extends HttpServlet {

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //lancio la pagina di home
        TemplateController.process("home.ftl", datamodel, response , this.getServletContext() );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
