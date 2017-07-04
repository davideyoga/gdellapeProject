package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.ServiceDao;
import model.Service;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */

public class AdmGetListUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Controlli su sessione e permessi, se va tutto a buon fine lancio il template con la lista degli utenti
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione valida
        if(this.sessionManager.isValid(request)){

            //controllo permessi

            //estraggo il servizio di creazione degli utenti
            Service modUser = utilityManager.getServiceAndCreate(request,response,ds,"modUser","Permissed for modification User",
                                                                    datamodel, getServletContext());

            //se l'utente in sessione possiede il servizio modUser...
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                //estraggo tutti gli utenti

                //lancio il template con la lista di tutti gli utenti

            } else {

                //lancio il messaggio di servizio non permesso
                TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
            }


        }else{//se sessione non valida

            //setto la pagina precedente e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request, response, "AdmGetListUser");

        }


    }
}
