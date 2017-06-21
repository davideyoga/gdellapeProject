package controller;

import controller.logController.SingletonLogManager;
import controller.sessionController.SingletonSessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Davide Micarelli
 * Classe che assegna il sessionManager e il logManager
 */
public class BaseController extends HttpServlet {

    SingletonSessionManager sessionManager;

    SingletonLogManager logManager;

    @Override
    public void init() throws ServletException {
        super.init();

        this.sessionManager = SingletonSessionManager.getSessionManager();

        this.logManager = SingletonLogManager.getLogManager();
    }
}
