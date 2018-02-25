package controller.adm.groups;


import controller.BaseController;
import controller.logController.LogException;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.GroupsServiceDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.GroupsServiceDao;
import dao.interfaces.ServiceDao;
import model.Groups;
import model.GroupsService;
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
 *
 * ERRORE: CONTROLLARE ESISTENZA PARAMETRO GET
 */
public class AdmModGroups extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Raccoglie i parametri post, tratta separatamente la modifica dei dati degli utenti con la modifica
     * delle associazioni con i gruppi degli utenti
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //pulisco messaggio
            datamodel.put("message", null);

            //se la session e' valida e abbastanza nuova
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modGroups = this.getServiceAndCreate(request, response, ds, "modGroups", "Permission for modification Groups",
                        datamodel, getServletContext());

                //se l'utente in sessione possiede il servizio modGroups...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modGroups)) {

                    //inizializzo il gruppo per renderli visibili nel blocco catch
                    Groups groupsDaForm = null;
                    Groups groupsPrimaDelleModifiche = null;


                    //inizializzo il dao
                    ServiceDao serviceDao = new ServiceDaoImpl(ds);
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    serviceDao.init();
                    groupsDao.init();

                    //controllo se nella sessione e' presente l'id del gruppo da modificare (non dovrebbe mai succedere)
                    if( request.getSession().getAttribute("idGroupsToModify") == null ||
                            request.getSession().getAttribute("idGroupsToModify").equals("")){

                        //lancio servlet della lista dei gruppi
                        response.sendRedirect("AdmGetListGroups");

                    }

                    //estraggo il gruppo prima della modifica
                    //nota: l'id del gruppo che si intende modificare viene inserito nel metodo doGet, quindi sara' sempre presente,
                    groupsPrimaDelleModifiche = groupsDao.getGroupsById((int) request.getSession().getAttribute("idGroupsToModify"));

                    //controllo sulla corretta estrazione del gruppo
                    if (groupsPrimaDelleModifiche == null || groupsPrimaDelleModifiche.getId() <= 0) {

                        //lancio servlet di errore
                        response.sendRedirect("Error");

                    }

                    //estraggo i servizi di propieta' del gruppo prima della modifica
                    List <Service> serviceListPrima = serviceDao.getServicesByGroup(groupsPrimaDelleModifiche);

                    //estraggo tutti i servizi
                    List <Service> serviceListAll = serviceDao.getAllService();

                    /*
                     * INIZIO MOD PROFILO GRUPPO
                     */

                    //creo un GRUPPO da riempire con i dati provenienti dalla form
                    groupsDaForm = groupsDao.getGroups();

                        /*
                         * SETTO USER DA FORM CON I DATI DALLA FORM
                         */
                    groupsDaForm.setId(groupsPrimaDelleModifiche.getId());
                    groupsDaForm.setName(request.getParameter("name"));
                    groupsDaForm.setDescription(request.getParameter("description"));

                    //se il gruppo e' cambiato rispetto a prima
                    if (!groupsDaForm.equals(groupsPrimaDelleModifiche)) {

                        //effettuo l'update
                        groupsDao.storeGroups(groupsDaForm);

                        logManager.addLog(sessionManager.getUser(request), "GROUPS: " + groupsPrimaDelleModifiche.toStringForLog() + " IT'S CHANGE IN: " + groupsDaForm.toStringForLog(), ds);

                    }
                    //se non e' cambiato non faccio nulla

                    /*
                     * FINE MOD PROFILO GRUPPO
                     */


                    /*
                     * INIZIO RACCOLTA SERVIZI
                     */

                    //inizializzo il dao degli userGroups
                    GroupsServiceDao groupsServiceDao = new GroupsServiceDaoImpl(ds);
                    groupsServiceDao.init();

                    //inizializzo un UserGroups per effettuare le operazioni
                    GroupsService groupsService = groupsServiceDao.getGroupsSerivce();

                    //creo una lista con i nomi dei servizi che mi arrivano dalla form
                    List <String> nameServiceListDopo = new ArrayList <>();

                    //ciclo sulla lista di tutti i servizi per estrarre i servizi dalla form
                    for (Service service : serviceListAll) {

                        //se l'admin ha ceckato sul ceckbox del servizio service
                        if (request.getParameter(service.getName()) != null) {

                            //aggiungo il nome del servizio alla lista dei nomi dei servizi dalla ceckati dalla form
                            nameServiceListDopo.add(request.getParameter(service.getName()));
                        }
                    }

                    //inizializzo un booleano per capire se devo aggiungere un log
                    boolean serviziCambiati = false;

                    //per chiarimenti maggiori di quello fatto sotto andare nella servlet AdmModUser, e' lo stesso principio

                    //ciclo la lista dei gruppi
                    for (Service service : serviceListAll) {

                        //caso 3, se il servizio e' presente in serviceListPrima ma non in nameServiceList
                        if (serviceListPrima.contains(service) && !nameServiceListDopo.contains(service.getName())) {

                            //tolgo il servizio service al gruppo

                            //setto il groupsService
                            groupsService.setIdGroups(groupsPrimaDelleModifiche.getId());
                            groupsService.setIdService(service.getId());

                            //elimino la connessione tra groups e service
                            groupsServiceDao.deleteGroupsService(groupsService);

                            serviziCambiati = true;

                        }

                        //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                        if (!serviceListPrima.contains(service) && nameServiceListDopo.contains(service.getName())) {

                            //aggiungo il servizio al gruppo

                            //setto il groupsUser
                            groupsService.setIdGroups(groupsPrimaDelleModifiche.getId());
                            groupsService.setIdService(service.getId());

                            System.out.println("groupsService: " + groupsService);

                            //creo la connessione tra groups e service
                            groupsServiceDao.insertGroupsService(groupsService);

                            serviziCambiati = true;

                        }

                        //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                    }

                    if (serviziCambiati == true) {

                        //aggiungo log di modifica associazioni con i servizi
                        logManager.addLog(sessionManager.getUser(request), "GROUPS: " + groupsDaForm.toStringForLog() + " HAS SUBMITTED CHANGES TO ASSOCIATED SERVICE", ds);

                    }


                    /*
                     * FINE RACCOLTA SERVIZI
                     */


                    //FINITO, LANCIO IL TEMPLATE CON I DATI CARICATI

                    //mi prendo i nuovi servizi per ricaricarli nel template
                    List <Service> newListService = serviceDao.getServicesByGroup(groupsDaForm);

                    //carico i servizi a cui ha accesso il gruppo
                    //per farlo ho bisogno dei nuovi servizi
                    datamodel.put("listGroupsService", newListService);

                    //carico tutti i servizi presenti nel sistema
                    datamodel.put("listService", serviceListAll);

                    //carico il nuovo gruppo nel template
                    datamodel.put("groups", groupsDaForm);

                    //chiudo tutto
                    groupsService = null;
                    groupsServiceDao.destroy();
                    groupsServiceDao = null;
                    groupsDao.destroy();
                    groupsDao = null;
                    serviceDao.destroy();
                    serviceDao = null;

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template di modifica dell'utente
                    TemplateController.process("groups_mod_adm.ftl", datamodel, response, getServletContext());


                    //se non ha il permesso
                } else {

                    //lancio servlet di servizio non permesso
                    response.sendRedirect("ServiceNotPermissed");
                }
                //se isHardValid = false
            } else {
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListGroups");
            }

        } catch (DaoException | LogException e) {
            //lancio servlet di errore
            response.sendRedirect("Error");
        }

    }


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
        try {

            //se la sessione e' valida
            if (sessionManager.isValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service modGroups = this.getServiceAndCreate(request, response, ds, "modGroups", "Permissed for modification Groups",
                        datamodel, getServletContext());

                //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modGroups)) {


                    //inizializzo i dao
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    ServiceDao serviceDao = new ServiceDaoImpl(ds);
                    serviceDao.init();

                    //se non e' presente il parametro GET id del gruppo da modificare o e' nullo
                    if (request.getParameter("id") == null || request.getParameter("id").equals("")) {
                        response.sendRedirect("AdmGetListGroups");
                        return;
                    }

                    //estraggo l'id del gruppo passato tramite get
                    int idGroups = Integer.parseInt(request.getParameter("id"));

                    //estraggo il gruppo tramite l'id passato dalla richiesta get
                    Groups groups = groupsDao.getGroupsById(idGroups);

                    //estraggo i servizi a cui ha accesso il gruppo
                    List <Service> listGroupsService = serviceDao.getServicesByGroup(groups);

                    //estraggo tutti i gruppi
                    List <Service> listService = serviceDao.getAllService();

                    //carico nel template il gruppo
                    datamodel.put("groups", groups);


                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                    request.getSession().setAttribute("idGroupsToModify", groups.getId());


                    //carico i servizi a cui appartiene il gruppo
                    datamodel.put("listGroupsService", listGroupsService);

                    //carico tutti i servizi presenti nel sistema
                    datamodel.put("listService", listService);

                    //chiudo i dao
                    groupsDao.destroy();
                    groupsDao = null;

                    serviceDao.destroy();
                    serviceDao = null;

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template di modifica del gruppo
                    TemplateController.process("groups_mod_adm.ftl", datamodel, response, getServletContext());


                    //se l'utente in sessione non ha i permessi
                } else {

                    //lancio servlet di servizio non permesso
                    this.processNotPermitted(request, response);
                }
                //se la sessione non e' valida
            } else {

                //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListGroups");

            }

        } catch (DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
        }
    }
}
