package controller;

import view.TemplateController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 */
public class ServiceNotPermissed extends BaseController {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio template di servizio non permesso
        TemplateController.process("not_permitted.ftl", datamodel,response,getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
