package controller.adm.user;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import model.Service;
import model.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 * Riceve dalla lista degli utenti una chiamata get con l'id del gruppo da eliminare
 * Una volta eliminato utente con id passato richiama servlet AdmGetListUser
 *
 * PARAMETRI RICHIESTI:
 * id: id dell'utente da eliminare
 */
public class DeleteUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    /**
     * Riceve dalla lista degli utenti una chiamata get con l'id del gruppo da eliminare
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se la sessione e' valida
        if(sessionManager.isValid(request)) {

            //estraggo il servizio di eliminazione degli utenti
            Service deleteUser = this.getServiceAndCreate(request, response, ds, "deleteUser",
                    "Permissed for delete User",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List<Service>) request.getSession().getAttribute("services")).contains(deleteUser)) {

                UserDao userDao = null;

                try {

                    //inizializzo il dao dei gruppi
                    userDao = new UserDaoImpl(ds);
                    userDao.init();

                    //estraggo dalla chiamata GET l'id del gruppo da eliminare e lo elimino
                    //(faccio un piccolo strappo alla regola di passare sempre gli oggetti, in modo da non dover estrarre il gruppo per poi eliminarlo)

                    User user = userDao.getUser();
                    user.setId(Integer.parseInt(request.getParameter("id")));

                    //controllo se l'utente sta cancellando se stesso
                    if(user.equals(sessionManager.getUser(request))){

                        //inserisco messaggio al template
                        datamodel.put("message","It is not possible to cancel himself");

                    }else {

                        //elimino l'utente
                        userDao.deleteUser(user);

                        //chiudo il dao
                        userDao.destroy();
                        userDao = null;

                        //inserisco un log
                        logManager.addLog(sessionManager.getUser(request), "USER: " + user + " HAS BEEN DELETED BY:" + sessionManager.getUser(request), ds);

                    }


                    //reindirizzo verso la servlet che si occupa di restituire la lista degli utenti
                    response.sendRedirect("AdmGetListUser");

                } catch (DaoException e) {

                    //esplicito la cancellazione del garbage collector
                    userDao = null;

                    //lancio messaggio di errore interno
                    this.processError(request, response);

                } catch (IOException e) {

                    //dovuto alla redirect;
                    //lancio messaggio di errore interno
                    this.processError(request, response);

                } catch (LogException e) {

                    //lancio messaggio di errore interno
                    datamodel.put("message", "Deleted group but no log entered");
                    this.processError(request, response);

                }

                //se non ha i permessi
            }else{
                //lancio il template di non permesso
                this.processNotPermitted(request, response);
            }
            //se session non valida o non abbastanza nuova
        }else{

            //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
            createPreviousPageAndRedirectToLogin(request,response,"AdmGetListUser");
        }
    }
}
