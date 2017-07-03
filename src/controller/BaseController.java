package controller;

import controller.logController.LogManager;
import controller.logController.SingletonLogManager;
import controller.sessionController.SessionException;
import controller.sessionController.SessionManager;
import controller.sessionController.SingletonSessionManager;
import controller.utility.SingletonUtilityManager;
import controller.utility.UtilityManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Davide Micarelli
 * Classe che assegna il sessionManager e il logManager
 */
public class BaseController extends HttpServlet {

    SessionManager sessionManager;

    LogManager logManager;

    UtilityManager utilityManager;

    @Override
    public void init() throws ServletException {
        super.init();

        this.sessionManager = SingletonSessionManager.getSessionManager();

        this.logManager = SingletonLogManager.getLogManager();

        this.utilityManager = controller.utility.SingletonUtilityManager.getUtilityManager();
    }


    public void createPreviousPageAndRedirectToLogin(HttpServletRequest request, HttpServletResponse response, String servletName){

        try {
            //creo la sessione se non esiste e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            request.getSession(true);

            sessionManager.setPreviusPage(request, servletName);

            //reindirizzo verso servlet di login
            response.sendRedirect("Login");

        } catch (SessionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
