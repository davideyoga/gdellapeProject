package controller;

import controller.session.SessionManager;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import view.TemplateController;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
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

        //non serve estrarre la sessione in quanto si trova nella request
        //HttpSession session = request.getSession(false);

        //se la sessione e' valida e abbastanza nuova
        if(SessionManager.isHardValid( request )){

            //prendo i dati dalla form e modifico l'utente


            //creo un nuovo log con i dati modificati

        }else{

            //non serve distruggere la sessione,
            //SessionManager.destroySession(request);

            //creo la sessione e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("previus_page", "ProfileManagement");

            //lancio il template di login
            TemplateController.process( "profile.html", datamodel ,response, getServletContext() );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione non valida
        if(!SessionManager.isValid(request)){;

            //ditruggo la sessione
            SessionManager.destroySession(request);

            //creo la sessione e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("previus_page", "ProfileManagement");

            //reindirizzo verso pagina di login
            response.sendRedirect("Login");
        }

        //estraggo la sessione se esiste
        HttpSession session = request.getSession();

        //inserisco l'user estratto dalla sessione da passate al template
        this.datamodel.put("user", session.getAttribute("user"));

        //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
        TemplateController.process( "profile.html", datamodel ,response, getServletContext() );

    }
}
