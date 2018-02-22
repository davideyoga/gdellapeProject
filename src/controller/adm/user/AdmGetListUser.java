package controller.adm.user;

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
 *
 * TEMPLATE LANCIATO: user_list_adm.ftl
 *
 * PARAMETRI DA PASSARE: niente
 *
 *
 */

public class AdmGetListUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service viewUser = this.getServiceAndCreate(request, response, ds, "viewUser", "Permitted for view User",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modUser...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(viewUser)) {

                    //inizializzo la lista di tutti gli utenti
                    List <User> userList = new ArrayList <>();


                    //inizializzo il dao degli utenti
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    //estraggo tutti gli utenti
                    userList = userDao.getUsers();

                    //chiudo il dao
                    userDao.destroy();
                    userDao = null;

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template con gli utenti caricati
                    datamodel.put("users", userList);


                    TemplateController.process("user_list_adm.ftl", datamodel, response, getServletContext());


                } else {

                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }


            } else {//se sessione non valida

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListUser");

            }

        } catch (Exception e) {
            //in caso di dao exception ecc. lancio il template di errore
            System.out.println(e.getMessage());
            this.processError(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request, response);

    }

    /**
     * Controlli su sessione e permessi, se va tutto a buon fine lancio il template con la lista degli utenti
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
