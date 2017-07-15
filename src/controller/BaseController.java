package controller;

import java.util.Map;

import com.sun.javafx.collections.MappingChange;
import controller.logController.LogManager;
import controller.logController.SingletonLogManager;
import controller.sessionController.SessionException;
import controller.sessionController.SessionManager;
import controller.sessionController.SingletonSessionManager;
import controller.utility.UtilityManager;
import dao.exception.DaoException;
import dao.interfaces.UserDao;
import model.User;
import view.TemplateController;

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

    public SessionManager sessionManager;

    public LogManager logManager;

    public UtilityManager utilityManager;

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

    /**
     * Torna true se esiste nel sistema un utente con la stessa mail, false altrimenti.
     * Il dao passato deve essere gia inizializzato con la init
     * @param userDao
     * @param email
     * @return
     * @throws DaoException
     */
    public boolean isExistEmail(UserDao userDao, String email) throws DaoException {

        User user = userDao.getUserByEmail(email);

        if(user == null || user.getId()<= 0) return false;
        else return true;

    }

    /**
     * Si occupa di lanciare il template template name nella giusta lingua caricata in sessione
     * @param request
     * @param response
     * @param templateName
     */
    public void processTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, Map<String, Object> datamodel){

        //se non e' settato nessun parametro della lingua:
        if( request.getParameter("lng") == null ){

            templateName = templateName+".ftl";
            TemplateController.process(templateName, datamodel, response, getServletContext());
        }else {
            if (request.getParameter("lng").equals("IT")) {
                templateName = templateName + ".ftl";
                TemplateController.process(templateName, datamodel, response, getServletContext());
            } else {

                templateName = templateName + "_en.ftl";
                TemplateController.process(templateName, datamodel, response, getServletContext());
            }
        }
    }

    /**
     * Setta nel datamodel passato la lingua a seconda della lingua in cui si trova attualmente il sito
     * @param request
     * @param datamodel
     */
    public void setLng(HttpServletRequest request, Map<String, Object> datamodel){

        //se non e' settato nessun parametro della lingua:
        if( request.getParameter("lng") == null ){

            datamodel.put("lng","IT");

        }else {
            //se ho la lingua settata in ita
            if (request.getParameter("lng").equals("IT")) {
                datamodel.put("lng", "IT");

                //ho la lingua settata in inglese
            } else {
                datamodel.put("lng", "EN");
            }
        }
    }
}
