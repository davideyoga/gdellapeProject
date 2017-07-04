package controller.adm;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.ServiceDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.ServiceDao;
import dao.interfaces.UserDao;
import model.Service;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
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

        this.doGet(request, response);

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

            //estraggo il servizio di creazione degli utenti
            Service modUser = utilityManager.getServiceAndCreate(request,response,ds,"modUser","Permissed for modification User",
                                                                    datamodel, getServletContext());

            //se l'utente in sessione possiede il servizio modUser...
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                //inizializzo la lista di tutti gli utenti
                List<User> userList = new ArrayList <>();

                try {

                    //inizializzo il dao degli utenti
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    //estraggo tutti gli utenti
                    userList = userDao.getUsers();

                    //chiudo il dao
                    userDao.close();
                    userDao = null;

                } catch (Exception e) {
                    //in caso di dao exception ecc. lancio il template di errore
                    TemplateController.process("error.ftl", datamodel,response,getServletContext());
                }

                //lancio il template con gli utenti caricati
                datamodel.put("users", userList);
                TemplateController.process( "user_list.ftl", datamodel ,response, getServletContext() );


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
