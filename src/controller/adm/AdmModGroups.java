package controller.adm;


import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.ServiceDao;
import model.Groups;
import model.Service;
import view.TemplateController;

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
public class AdmModGroups extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();





    /**
     * Riceve dal template AdmGetListuser i parametri get dell'utente, mantengo l'id in sessione dell'utente da
     * modificare per riestrarre l'utente nella richiesta post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se la sessione e' valida
        if(sessionManager.isValid(request)){

            //estraggo il servizio di creazione degli utenti
            Service modGroups = utilityManager.getServiceAndCreate(request,response,ds,"modGroups","Permissed for modification Groups",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modGroups)) {

                try {
                    //inizializzo i dao
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    ServiceDao serviceDao = new ServiceDaoImpl(ds);
                    serviceDao.init();

                    //estraggo l'id del gruppo passato tramite get
                    int idGroups = Integer.parseInt(request.getParameter("id"));

                    //estraggo il gruppo tramite l'id passato dalla richiesta get
                    Groups groups = groupsDao.getGroupsById( idGroups);

                    //estraggo i servizi a cui ha accesso il gruppo
                    List<Service> listGroupsService = serviceDao.getServicesByGroup(groups);

                    //estraggo tutti i gruppi
                    List<Service> listService = serviceDao.getAllService();

                    //carico nel template il gruppo
                    datamodel.put("groups", groups);




                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                    request.getSession().setAttribute("idGroupsToModify", groups.getId() );




                    //carico i servizi a cui appartiene il gruppo
                    datamodel.put("listGroupsService", listGroupsService);

                    //carico tutti i servizi presenti nel sistema
                    datamodel.put("listService", listService);

                    //chiudo i dao
                    groupsDao.destroy();
                    groupsDao = null;

                    serviceDao.destroy();
                    serviceDao = null;

                    //lancio il template di modifica del gruppo
                    TemplateController.process("groups_mod.ftl", datamodel,response,getServletContext());

                } catch (DaoException e) {
                    //in caso di dao exception ecc. lancio il template di errore
                    TemplateController.process("error.ftl", datamodel,response,getServletContext());
                }


                //se l'utente in sessione non ha i permessi
            }else{

                //lancio il template di non permesso
                TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
            }
            //se la sessione non e' valida
        }else{

            //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
            createPreviousPageAndRedirectToLogin(request,response,"AdmGetListGroups");

        }
    }
}
