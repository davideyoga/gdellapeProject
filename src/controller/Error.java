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
public class Error extends BaseController {

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio la pagina di errore
        TemplateController.process("error.ftl", datamodel, response , this.getServletContext() );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
