package controller.adm.user;

import controller.BaseController;
import controller.logController.LogException;
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
 *
 * TEMPLATE LANCIATO: user_mod.ftl
 *
 * PARAMETRI DA PASSARE:
 * id: id dell'utente che si intende modificare
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

        try {
            //pulisco messaggio
            datamodel.put("message", null);

            //se la session ee' valida e abbastanza nuova
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = this.getServiceAndCreate(request, response, ds, "modUser", "Permissed for modification User",
                        datamodel, getServletContext());

                //se l'utente ha il permesso (potrebbe essere ridondante in quanto viene controllato anche per accedere alla lista ma nn si sa mai)
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {

                    //inizializzo gli user per renderli visibili nel blocco catch
                    User userDaForm = null;
                    User userPrimaDelleModifiche = null;


                    //inizializzo il dao
                    UserDao userDao = new UserDaoImpl(ds);
                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    userDao.init();
                    groupsDao.init();


                    //estraggo l'utente prima della modifica
                    //nota: l'id dell'utente che si intende modificare viene inserito nel metodo doGet, quindi sara' sempre presente,
                    userPrimaDelleModifiche = userDao.getUserById((int) request.getSession().getAttribute("idUserToModify"));

                    //se l'utente estratto e' valido
                    if (userPrimaDelleModifiche != null && userPrimaDelleModifiche.getId() > 0) {


                        //estraggo i gruppi a cui appartiene l'utente prima della modifica
                        List <Groups> groupsListPrima = groupsDao.getGroupsByUser(userPrimaDelleModifiche);

                        //estraggo tutti i gruppi
                        List <Groups> groupsListAll = groupsDao.getAllGroups();

                        /*
                        * INIZIO MOD PROFILO
                        */

                        //creo un user da riempire con i dati provenienti dalla form
                        userDaForm = userDao.getUser();

                        userDaForm.setPassword(request.getParameter("password"));

                        System.out.println("Password Nuovo utente prima: "+request.getParameter("password"));

                        System.out.println("Password Vecchio Utente: "+userPrimaDelleModifiche.getPassword());

                        boolean ceckPassword = false;
                        //se la password dell'utente non e' stata inserita la recupero dal vecchio user
                        if(userDaForm.getPassword().equals(null) || userDaForm.getPassword().equals("") ){
                            userDaForm.setPassword(userPrimaDelleModifiche.getPassword());
                            ceckPassword=true;
                        }

                        System.out.println("Password Nuovo Utente dopo: "+userDaForm.getPassword());


                        //se la password inserita e' uguale in entrambi i campi
                        if (userDaForm.getPassword().equals(request.getParameter("ripetere-password")) || ceckPassword) {

                            System.out.println("SONO ENTRATO!!!");

                            //se la mail non e' presente nel sistema oppure e' uguale alla mail precedente
                            if (!isExistEmail(userDao, request.getParameter("email")) || request.getParameter("email").equals(userPrimaDelleModifiche.getEmail())) {

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

                                /*
                                    CONTROLLO SE IL NUMERO E' UN NUMERO E NON UN CARATTERE
                                 */

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

                                System.out.println("User Prima: " + userPrimaDelleModifiche);

                                System.out.println("User Dopo: " + userDaForm);


                                //se l'utente e' cambiato rispetto a prima
                                if (!userDaForm.equals(userPrimaDelleModifiche)) {

                                    System.out.println("Effettoato update");

                                    userDaForm.setPassword(utilityManager.sha1Encrypt(userDaForm.getPassword()));

                                    //effettuo l'update
                                    userDao.storeUser(userDaForm);

                                    datamodel.put("message", "Utente aggiornato con successo");

                                    logManager.addLog(sessionManager.getUser(request), "USER " + userPrimaDelleModifiche.toStringForLog() + " IT'S CHANGE: " + userDaForm.toStringForLog(), ds);

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
                                List <String> nameGroupsListDopo = new ArrayList <>();

                                //ciclo sulla lista di tutti i gruppi per estrarre i gruppi dalla form
                                for (Groups groups : groupsListAll) {

                                    //se l'admin ha ceckato sul ceckbox del gruppo groups
                                    if (request.getParameter(groups.getName()) != null) {

                                        //aggiungo il nome del gruppo alla lista dei nomi dei gruppi dalla ceckati dalla form
                                        nameGroupsListDopo.add(request.getParameter(groups.getName()));
                                    }
                                }

                                //inizializzo un booleano per capire se devo aggiungere un log
                                boolean gruppiCambiati = false;

                                //groupsListAll: tutti i gruppi estratti dopo aver premuto sul pulsante della form
                                //groupsListPrima: gruppi a cui era collegato l'utente prima
                                //nameGroupsListDopo: nomi dei gruppi ceckati nella form

                                //devo confrontare i nomi dei gruppi di groupsListPrima con nameGroupsListDopo

                                //3 possibilita':
                                //CASO 1: il gruppo e' presente sia in groupsListPrima che in nameGroupsListDopo: non faccio nulla
                                //CASO2: il gruppo e' stato aggiunto, quindi non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo: aggiungo il gruppo all'utente
                                //CASO3: il gruppo e' stato tolto, quindi e' presente in groupsListPrima ma non in nameGroupsList: tolgo il gruppo all'utente

                                //ciclo la lista contenente tutti i gruppi
                                for (Groups groups : groupsListAll) {

                                    //caso 3, se il gruppo e' presente in groupsListPrima ma non in nameGroupsList
                                    if (groupsListPrima.contains(groups) && !nameGroupsListDopo.contains(groups.getName())) {

                                        //tolgo il gruppo groups all'utente

                                        //setto l'userGroups
                                        userGroups.setIdUser(userPrimaDelleModifiche.getId());
                                        userGroups.setIdGroups(groups.getId());

                                        //elimino la connessione tra user e groups
                                        userGroupsDao.deleteUserGroups(userGroups);

                                        gruppiCambiati = true;

                                    }

                                    //caso 2, se groups non e' contenuto in groupsListPrima ed ora e' contenuto in nameGroupsListDopo
                                    if (!groupsListPrima.contains(groups) && nameGroupsListDopo.contains(groups.getName())) {

                                        //aggiungo il gruppo all'utente

                                        //setto l'userGroups
                                        userGroups.setIdUser(userPrimaDelleModifiche.getId());
                                        userGroups.setIdGroups(groups.getId());

                                        //creo la connessione tra user e groups
                                        userGroupsDao.insertUserGroups(userGroups);

                                        gruppiCambiati = true;
                                    }

                                    //se non sono veri nessuno dei due ci troviamo nel caso 1, quindi non faccio nulla

                                }

                                if (gruppiCambiati == true) {

                                    //aggiungo log di modifica associazioni con i gruppi
                                    logManager.addLog(sessionManager.getUser(request), "USER: " + userDaForm.toStringForLog() + " HAS SUBMITTED CHANGES TO ASSOCIATED GROUPS", ds);

                                }


                                /*
                                * FINE RACCOLTA GRUPPI
                                */


                                //FINITO, LANCIO IL TEMPLATE CON I DATI CARICATI

                                //mi prendo i nuovi gruppi per ricaricarli nel template
                                List <Groups> newListGroups = groupsDao.getGroupsByUser(userDaForm);

                                //carico i gruppi a cui appartiene l'utente
                                //per farlo ho bisogno dei nuovi gruppi
                                datamodel.put("listUserGroups", newListGroups);

                                //carico tutti i gruppi presenti nel sistema
                                datamodel.put("listGroups", groupsListAll);

                                utilityManager.removePassword(userDaForm);

                                //carico il nuovo utente nel template
                                datamodel.put("usermod", userDaForm);

                                //chiudo tutto
                                userGroups = null;
                                userGroupsDao.destroy();
                                userGroupsDao = null;
                                userDao.destroy();
                                userDao = null;
                                groupsDao.destroy();
                                groupsDao = null;

                                //setto l'utente in sessione
                                this.datamodel.put("user", sessionManager.getUser(request));

                                //lancio il template di modifica dell'utente
                                TemplateController.process("user_mod.ftl", datamodel, response, getServletContext());


                            } else {//se la mail esiste gia' nel db

                                //chiudo tutto
                                userDao.destroy();
                                userDao = null;
                                groupsDao.destroy();
                                groupsDao = null;

                                //carico un messaggio di errore
                                datamodel.put("message", "Error, Email already exists in the system, No changes have been made");

                                utilityManager.removePassword(userPrimaDelleModifiche);

                                datamodel.put("usermod", userPrimaDelleModifiche);

                                //setto l'utente in sessione
                                this.datamodel.put("user", sessionManager.getUser(request));

                                //lancio il template di modifica dell'utente
                                TemplateController.process("user_mod.ftl", datamodel, response, getServletContext());
                            }


                            //se le password non sono uguali
                        } else {

                            //chiudo tutto
                            userDao.destroy();
                            userDao = null;
                            groupsDao.destroy();
                            groupsDao = null;

                            //carico un messaggio di errore
                            datamodel.put("message", "Error, The entered passwords are different, No changes have been made");
                            datamodel.put("user", userPrimaDelleModifiche);

                            //setto l'utente in sessione
                            this.datamodel.put("user", sessionManager.getUser(request));

                            //lancio il template di modifica dell'utente
                            TemplateController.process("user_mod.ftl", datamodel, response, getServletContext());
                        }

                        //se l'utente estratto non e' valido
                    } else {
                        //lancio servlet di errore
                        response.sendRedirect("Error");
                    }


                    //se non ha il permesso
                } else {

                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);
                }
                //se isHardValid = false
            } else {
                //setto la pagina precedente e reindirizzo al login
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListUser");
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
            //pulisco messaggio
            datamodel.put("message", null);

            //se la sessione e' valida
            if (sessionManager.isValid(request)) {

                //estraggo il servizio di creazione degli utenti
                Service modUser = this.getServiceAndCreate(request, response, ds, "modUser", "Permissed for modification User",
                        datamodel, getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(modUser)) {


                    //inizializzo i dao
                    UserDao userDao = new UserDaoImpl(ds);
                    userDao.init();

                    GroupsDao groupsDao = new GroupsDaoImpl(ds);
                    groupsDao.init();

                    //se non e' presente l'id dell'utente passato come parametro get c'e' un problema
                    if (request.getParameter("id") == null) {
                        response.sendRedirect("AdmGetListUser");
                        return;
                    }

                    //estraggo l'id dell'utente passato trmaite get
                    int idUser = Integer.parseInt(request.getParameter("id"));

                    //estraggo l'utente tramite l'id passato dalla richiesta get
                    User user = userDao.getUserById(idUser);

                    //estraggo i gruppi a cui appartiene l'utente
                    List <Groups> listUserGroups = groupsDao.getGroupsByUser(user);

                    //estraggo tutti i gruppi
                    List <Groups> listGroups = groupsDao.getAllGroups();

                    //elimino la password
                    utilityManager.removePassword(user);

                    //carico nel template l'utente
                    datamodel.put("usermod", user);

                    //prima di lanciara il template carico nella sessione dell'amministatore l'id dell'utente che intendo modificare
                    request.getSession().setAttribute("idUserToModify", user.getId());

                    //carico i gruppi a cui appartiene l'utente
                    datamodel.put("listUserGroups", listUserGroups);

                    //carico tutti i gruppi presenti nel sistema
                    datamodel.put("listGroups", listGroups);

                    //chiudo i dao
                    userDao.destroy();
                    userDao = null;

                    groupsDao.destroy();
                    groupsDao = null;

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template di modifica dell'utente
                    TemplateController.process("user_mod.ftl", datamodel, response, getServletContext());


                    //se l'utente in sessione non ha i permessi
                } else {

                    //lancio il template di non permesso
                    this.processNotPermitted(request, response);
                }
                //se la sessione non e' valida
            } else {

                //setto la pagina alla lista degli utenti in quanto questa chiamata avviene tramite GET
                createPreviousPageAndRedirectToLogin(request, response, "AdmGetListUser");

            }
        } catch (DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);
        }
    }
}
