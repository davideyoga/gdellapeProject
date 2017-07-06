package controller.adm;

import com.sun.xml.internal.bind.v2.model.core.ID;
import controller.BaseController;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.implementation.UserGroupsDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.UserDao;
import dao.interfaces.UserGroupsDao;
import model.Groups;
import model.Service;
import model.User;
import model.UserGroups;
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
public class AdmModUser extends BaseController {

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

        //se la session ee' valida e abbastanza nuova
        if(sessionManager.isHardValid(request)) {

            //estraggo il servizio di creazione degli utenti
            Service modUser = utilityManager.getServiceAndCreate(request, response, ds, "modUser", "Permissed for modification User",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                //inizializzo gli user per renderli visibili nel blocco catch
                User userDaForm = null;
                User userPrimaDelleModifiche = null;

                try {

                    //inizializzo il dao
                    UserDao userDao = new UserDaoImpl(ds);
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    userDao.init();
                    groupsDao.init();

                    //estraggo l'utente prima della modifica
                    userPrimaDelleModifiche = userDao.getUserById((int) request.getSession().getAttribute("idUserToModify"));

                    //estraggo i gruppi a cui appartiene l'utente prima della modifica
                    List<Groups> groupsListPrima = groupsDao.getGroupsByUser(userPrimaDelleModifiche);

                    //estraggo tutti i gruppi
                    List<Groups> groupsListAll = groupsDao.getAllGroups();

                    /*
                     * INIZIO MOD PROFILO
                     */

                    //creo un user da riempire con i dati provenienti dalla form
                    userDaForm = userDao.getUser();

                    userDaForm.setPassword(request.getParameter("password"));

                    System.out.println("Password 1: " + request.getParameter("password"));
                    System.out.println("Password 1: " + request.getParameter("ripetere-password"));

                    //se la password inserita e' uguale in entrambi i campi
                    if (userDaForm.getPassword().equals(request.getParameter("ripetere-password"))) {

                        /*
                         * SETTO USER DA FORM CON I DATI DALLA FORM
                         */
                        userDaForm.setId(userPrimaDelleModifiche.getId());
                        userDaForm.setEmail(request.getParameter("email"));
                        userDaForm.setSurname(request.getParameter("surname"));
                        userDaForm.setName(request.getParameter("name"));
                        userDaForm.setEmail(request.getParameter("email"));

                        //per castare la stringa in int
                        String numberDaForm = request.getParameter("number");

                        //se il numero estratto dalla form e' diverso da 0 e divero da ""
                        if (numberDaForm != null && numberDaForm != "") {

                            long number = Long.parseLong(numberDaForm);

                            userDaForm.setNumber(number);


                        } else {
                            userDaForm.setNumber(userPrimaDelleModifiche.getNumber());
                        }

                        userDaForm.setCurriculum_ita(request.getParameter("curriculum_ita"));
                        userDaForm.setCurriculum_eng(request.getParameter("curriculum_eng"));
                        userDaForm.setReceprion_hours_ita(request.getParameter("receprion_hours_ita"));
                        userDaForm.setReceprion_hours_eng(request.getParameter("receprion_hours_eng"));


                        System.out.println("user prima: " + userPrimaDelleModifiche);
                        System.out.println("user dopo:"+ userDaForm);

                        //se l'utente e' cambiato rispetto a prima
                        if(!userDaForm.equals(userPrimaDelleModifiche)){

                            //effettuo l'update
                            userDao.storeUser(userDaForm);
                        }
                        //se non e' cambiato non faccio nulla

                        /*
                        * FINE MOD PROFILO
                        */


                        /*
                         * INIZIO RACCOLTA GRUPPI
                         */

                        //inizializzo il dao degli userGroups
                        UserGroupsDao userGroupsDao = new UserGroupsDaoImpl(ds);
                        userGroupsDao.init();

                        //inizializzo un UserGroups per effettuare le operazioni
                        UserGroups userGroups = userGroupsDao.getUserGroups();

                        //creo una lista con i nomi dei gruppi che mi arrivano dalla form
                        List<String> nameGroupsListDopo = new ArrayList <>();

                        //ciclo sulla lista di tutti i gruppi per estrarre i gruppi dalla form
                        for( Groups groups:groupsListAll){

                            //se l'admin ha ceckato sul ceckbox del gruppo groups
                            if(request.getParameter(groups.getName()) != null){

                                //aggiungo il nome del gruppo alla lista dei nomi dei gruppi dalla ceckati dalla form
                                nameGroupsListDopo.add( request.getParameter(groups.getName()));
                            }
                        }

                        //groupsListAll: tutti i gruppi estratti dopo aver premuto sul pulsante della form
                        //groupsListPrima: gruppi a cui era collegato l'utente prima
                        //nameGroupsListDopo: nomi dei gruppi ceckati nella form

                        //devo confrontare i nomi dei gruppi di groupsListPrima con nameGroupsListDopo

                        //3 possibilita':
                            //CASO 1: il gruppo e' presente sia in groupsListPrima che in nameGroupsListDopo: non faccio nulla
                            //CASO2: il gruppo e' stato aggiunto, quindi non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo: aggiungo il gruppo all'utente
                            //CASO3: il gruppo e' stato tolto, quindi e' presente in groupsListPrima ma non in nameGroupsList: tolgo il gruppo all'utente

                        //ciclo la lista dei gruppi, SI PUO' OTTIMIZZARE
                        for (Groups groups:groupsListAll){

                            //caso 3, se il gruppo e' presente in groupsListPrima ma non in nameGroupsList
                            if( groupsListPrima.contains(groups) && !nameGroupsListDopo.contains(groups.getName()) ){

                                //tolgo il gruppo groups all'utente

                                //setto l'userGroups
                                userGroups.setIdUser(userPrimaDelleModifiche.getId());
                                userGroups.setIdGroups(groups.getId());

                                //elimino la connessione tra user e groups
                                userGroupsDao.deleteUserGroups(userGroups);

                            }

                            //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                            if(!groupsListPrima.contains(groups) && nameGroupsListDopo.contains(groups.getName())){

                                //aggiungo il gruppo all'utente

                                //setto l'userGroups
                                userGroups.setIdUser(userPrimaDelleModifiche.getId());
                                userGroups.setIdGroups(groups.getId());

                                //creo la connessione tra user e groups
                                userGroupsDao.insertUserGroups(userGroups);

                            }

                            //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                        }


                        /*
                         * FINE RACCOLTA GRUPPI
                         */


                        //FINITO, LANCIO IL TEMPLATE CON I DATI CARICATI

                        //mi prendo i nuovi gruppi per ricaricarli nel template, SI PUO' OTTIMIZZARE
                        List<Groups> newListGroups = groupsDao.getGroupsByUser(userDaForm);

                        //carico i gruppi a cui appartiene l'utente
                        //per farlo ho bisogno dei nuovi gruppi
                        datamodel.put("listUserGroups", newListGroups);

                        //carico tutti i gruppi presenti nel sistema
                        datamodel.put("listGroups", groupsListAll);

                        //carico il nuovo utente nel template
                        datamodel.put("user", userDaForm);

                        //chiudo tutto
                        userGroups = null;
                        userGroupsDao.destroy();
                        userGroupsDao = null;
                        userDao.destroy();
                        userDao=null;
                        groupsDao.destroy();
                        groupsDao = null;

                        //lancio il template di modifica dell'utente
                        TemplateController.process("user_mod.ftl", datamodel,response,getServletContext());


                    //se le password non sono uguali
                    }else{

                        //chiudo tutto
                        userDao.destroy();
                        userDao=null;
                        groupsDao.destroy();
                        groupsDao = null;

                        //carico un messaggio di errore
                        datamodel.put("message", "Errore, le password non sono uguali, non e' stata effettuata alcuna modifica");
                        datamodel.put("user", userPrimaDelleModifiche);

                        //lancio il template di modifica dell'utente
                        TemplateController.process("user_mod.ftl", datamodel,response,getServletContext());
                    }

                } catch (DaoException e) {
                    e.printStackTrace();
                }

                //se non ha il permesso
            } else {

                //lancio il messaggio di servizio non permesso
                TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );
            }
        //se isHardValid = false
        }else {
            //setto la pagina precedente e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request, response, "AdmGetListUser");
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

        //se la sessione e' valida
        if(sessionManager.isValid(request)){

            //estraggo il servizio di creazione degli utenti
            Service modUser = utilityManager.getServiceAndCreate(request,response,ds,"modUser","Permissed for modification User",
                    datamodel, getServletContext());

            //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
            if (((List<Service>) request.getSession().getAttribute("services")).contains(modUser)) {


                try {
                    //inizializzo i dao
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //estraggo l'id dell'utente passato trmaite get
                    int idUser = Integer.parseInt(request.getParameter("id"));

                    //estraggo l'utente tramite l'id passato dalla richiesta get
                    User user = userDao.getUserById( idUser);

                    //estraggo i gruppi a cui appartiene l'utente
                    List<Groups> listUserGroups = groupsDao.getGroupsByUser(user);

                    //estraggo tutti i gruppi
                    List<Groups> listGroups = groupsDao.getAllGroups();

                    //carico nel template l'utente
                    datamodel.put("user", user);




                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                    request.getSession().setAttribute("idUserToModify", user.getId() );




                    //carico i gruppi a cui appartiene l'utente
                    datamodel.put("listUserGroups", listUserGroups);

                    //carico tutti i gruppi presenti nel sistema
                    datamodel.put("listGroups", listGroups);

                    //chiudo i dao
                    userDao.destroy();
                    userDao = null;

                    groupsDao.destroy();
                    groupsDao= null;

                    //lancio il template di modifica dell'utente
                    TemplateController.process("user_mod.ftl", datamodel,response,getServletContext());

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
            createPreviousPageAndRedirectToLogin(request,response,"AdmGetListUser");

        }
    }
}
