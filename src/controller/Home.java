package controller;

import controller.sessionController.SessionException;
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
 * @author Davide Micarelli
 */
public class Home extends BaseController {

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //carico la lingua nel datamodel
        this.setLng(request, datamodel);

        System.out.println("lng: " + datamodel.get("lng"));

        //lancia il template appropriato alla lingua selezionata dall'utente
        this.processTemplate(request, response, "home", datamodel);
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
