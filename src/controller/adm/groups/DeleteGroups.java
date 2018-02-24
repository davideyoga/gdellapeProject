package controller.adm.groups;

import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.interfaces.GroupsDao;
import model.Groups;
import model.Service;

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
 */
public class DeleteGroups extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Riceve dalla lista dei gruppi una chiamata get con l'id del gruppo da eliminare
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se la sessione e' valida
        if(sessionManager.isValid(request)) {

            //estraggo il servizio di eliminazione dei gruppi
            Service deleteGroups = this.getServiceAndCreate(request, response, ds, "deleteGroups", "Permissed for delete Groups",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List <Service>) request.getSession().getAttribute("services")).contains(deleteGroups)) {

                GroupsDao groupsDao = null;

                try {

                    //inizializzo il dao dei gruppi
                    groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //estraggo dalla chiamata GET l'id del gruppo da eliminare e lo elimino
                    //(faccio un piccolo strappo alla regola di passare sempre gli oggetti, in modo da non dover estrarre il gruppo per poi eliminarlo)

                    Groups groups = groupsDao.getGroups();
                    groups.setId(Integer.parseInt(request.getParameter("id")));

                    //elimino il gruppo
                    groupsDao.deleteGroups(groups);

                    //chiudo il dao
                    groupsDao.destroy();
                    groupsDao = null;

                    //inserisco un log
                    logManager.addLog(sessionManager.getUser(request),"GROUPS: " + groups.toStringForLog() + " HAS BEEN DELETED BY:" + sessionManager.getUser(request).toStringForLog(),ds );

                    //reindirizzo verso la servlet che si occupa di restituire la lista dei gruppi
                    response.sendRedirect("AdmGetListGroups");

                } catch (DaoException e) {

                    //esplicito la cancellazione del garbage collector
                    groupsDao = null;

                    //lancio messaggio di errore interno
                    this.processError(request, response);

                } catch (IOException e) {

                    //dovuto alla redirect;
                    //lancio messaggio di errore interno
                    this.processError(request, response);

                } catch (LogException e) {

                    //lancio messaggio di errore interno
                    datamodel.put("message", "Deleted group but no log entered");
                    this.processError(request,response);

                }

                //se non ha i permessi
            }else{
                //lancio il template di non permesso
                this.processNotPermitted(request, response);
            }
        //se session non valida o non abbastanza nuova
        }else{

            //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
            createPreviousPageAndRedirectToLogin(request,response,"AdmGetListGroups");
        }
    }

}
