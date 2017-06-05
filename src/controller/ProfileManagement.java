package controller;

import controller.session.SessionManager;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */

public class ProfileManagement extends HttpServlet {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        //se la sessione e' valida e abbastanza nuova
        if(SessionManager.isHardValid( session, request)){

            //prendo i dati dalla form e modifico l'utente

            //creo un nuovo log con i dati modificati
        }

    }

    //estraggo i dati riguardanti tale utente nel db e li passo al template
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        //inserisco l'user estratto dalla sessione da passate al template
        this.datamodel.put("user", session.getAttribute("user"));

        //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
        TemplateController.process( "profile.html", datamodel ,response, getServletContext() );

    }
}
