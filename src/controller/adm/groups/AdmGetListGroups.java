package controller.adm.groups;

import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.interfaces.GroupsDao;
import model.Groups;
import model.Service;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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
public class AdmGetListGroups extends BaseController {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message",null);

            //se sessione valida
            if (this.sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modGroups = this.getServiceAndCreate(request, response, ds, "viewGroups", "Permitted for view Groups",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modGroups...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modGroups)) {

                    //inizializzo la lista di tutti i gruppi
                    List <Groups> groupsList = new ArrayList <>();


                    //inizializzo il dao dei gruppi
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //estraggo tutti i gruppi
                    groupsList = groupsDao.getAllGroups();

                    //chiudo il dao
                    groupsDao.destroy();
                    groupsDao = null;

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));


                    //lancio il template con i gruppi caricati
                    datamodel.put("groups", groupsList);
                    TemplateController.process("groups_list_adm.ftl", datamodel, response, getServletContext());


                    //se non possiede il servizio
                } else {

                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }


            } else {//se sessione non valida

                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListGroups");

            }
        } catch (DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            TemplateController.process("error.ftl", datamodel,response,getServletContext());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
