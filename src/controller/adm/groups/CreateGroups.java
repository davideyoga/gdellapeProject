package controller.adm.groups;

import controller.BaseController;
import controller.logController.LogException;
import controller.sessionController.SessionException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class CreateGroups extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    /**
     * Controlla sessione e permessi e se tutto va a buon fine lancia la pagina di creazione utente
     * @param request
     * @param response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //se sessione valida, uso hardValid perche' questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service createGroups = this.getServiceAndCreate(request, response, ds, "createGroup", "Service to create group", datamodel, this.getServletContext());

                //se l'utente in sessione possiede il servizio createGroups
                if (((List <Service>) request.getSession().getAttribute("services")).contains(createGroups)) {

                    //setto l'utente in sessione
                    this.datamodel.put("user", sessionManager.getUser(request));

                    //lancio il template di creazione
                    TemplateController.process("create_group.ftl", datamodel, response, getServletContext());

                } else {

                    //lancio il messaggio di servizio non permesso
                    this.processNotPermitted(request, response);

                }


                //se la sessione non e' valida
            } else {

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateGroups");

            }

        }catch (ServletException e){

            this.processError(request, response);

        }

    }

    /**
     * Controlla sessione e permessi e se va tutto bene crea il gruppo
     * @param request
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pulisco messaggio
        datamodel.put("message",null);

        //rendo visibile il dao nel blocco catch
        GroupsDao groupsDao = null;

        try {

            //se sessione valida, uso hardValid perche questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //estraggo il servizio di creazione dei gruppi
                Service createGroups = this.getServiceAndCreate(request,response,ds,"createGroup","Service to create group",datamodel, this.getServletContext());

                //se l'utente ha il permesso
                if (((List <Service>) request.getSession().getAttribute("services")).contains(createGroups)) {

                    //raccolgo i dati dalla form
                    String nameDaForm = request.getParameter("name");

                    String descriptionDaForm = request.getParameter("description");

                    //se i dati sono corretti
                    if( nameDaForm != null && nameDaForm != ""){

                        //inizializzo il GroupsDao
                        groupsDao = new GroupsDaoImpl(ds);
                        groupsDao.init();

                        //estraggo il gruppo con lo stesso nome
                        Groups groups = groupsDao.getGroupsByName(nameDaForm);

                        //se non esiste un gruppo con lo stesso nome procedo alla creazione
                        if (groups == null || groups.getId() <= 0) {

                            //faccio puntare user ad un utente che non sia null
                            groups = groupsDao.getGroups();

                            //setto il gruppo da inserire
                            groups.setName(nameDaForm);
                            groups.setDescription(descriptionDaForm);

                            //inserisco il gruppo
                            groupsDao.storeGroups(groups);

                            //aggiungo un log di avvenuta creazioendi un gruppo
                            logManager.addLog(sessionManager.getUser(request),"GROUP CREATED: " + groups.toStringForLog() + " BY: " + sessionManager.getUser(request).toStringForLog(), ds);

                            //se la mail e' gia presente nel database
                        } else {

                            //inserisco il messaggio di gruppo con lo stesso nome gia' presente nel database
                            datamodel.put("message", "gruppo con questo nome gia presente nel database");

                            //setto l'utente in sessione
                            this.datamodel.put("user", sessionManager.getUser(request));

                            //lancio la pagina di creazione dell'utente
                            TemplateController.process("create_group.ftl", datamodel, response, getServletContext());

                        }

                        groupsDao.destroy();
                        groupsDao = null;

                        //se i dati passati dalla form non sono corretti:
                    }else{
                        //inserisco il messaggio di dati non corretti
                        datamodel.put("message", "dati passati non corretti");

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //lancio la pagina di creazione del gruppo
                        TemplateController.process("create_group.ftl", datamodel, response, getServletContext());
                    }

                    //se all'utente non e' permesso aggiungere gruppi
                } else {

                    //lancio il template della pagina not_permissed
                    this.processNotPermitted(request, response);
                }

                //se la sessione non e' valida
            }else{

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateGroups");

            }

            //se va tutto a buon fine lancio la pagina di creazione del gruppo con il messaggio di avvenuta creazione

            //inserisco il messaggio utente creato
            datamodel.put("message", "Created Group");

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //lancio la pagina di creazione di un gruppo
            TemplateController.process("create_group.ftl", datamodel, response, getServletContext());

        }catch ( DaoException e) {
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {

            //rendo piu' facile il lavoro del garbage collector
            groupsDao = null;

            //inserisco il messaggio utente creato con messaggio di errore del log
            datamodel.put("message", "Created Group but log insert error");

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //lancio la pagina di creazione del gruppo
            TemplateController.process("create_group.ftl", datamodel, response, getServletContext());
        }
    }
}
