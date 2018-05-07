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
public class Home extends BaseController {

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //carico la lingua nel datamodel
        this.setLng(request, datamodel);

        //carico nome servlet
        this.datamodel.put("nameServlet","Home");

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
