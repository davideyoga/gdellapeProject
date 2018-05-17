package controller.adm.user;

import controller.BaseController;
import controller.logController.LogException;
import controller.sessionController.SessionException;
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
public class CreateUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    private Service getCreateUserService(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        //inizializzo il dao per estrarre il servizio createUser
        ServiceDao serviceDao = new ServiceDaoImpl(ds);

        Service createUser = null;

        try {

            //inizializzo query ecc...
            serviceDao.init();

            //estraggo il servizio createUser
            createUser = serviceDao.getServiceByName("createUser");

            //se il servizio non e' presente nel database o non ha un id valido lo creo
            if (createUser == null || createUser.getId() <= 0) {

                //faccio puntare createUser ad un servizio vuoto da riempire
                createUser = serviceDao.getService();

                //setto il servizio
                createUser.setName("createUser");
                createUser.setDescription("Service for create new user");

                //inserisco il servizio nel database
                serviceDao.storeService(createUser);
            }

        } catch (DaoException e) {

            datamodel.put("message", "internal error");

            //reindirizzo alla pagina di errore
            this.processError(request, response);
        }

        return createUser;

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione valida, uso hardValid perche questo processo implica un controllo di sicurezza
        if(sessionManager.isHardValid(request)) {

            //estraggo il servizio di creazione degli utenti
            Service createUser = getCreateUserService(request,response);

            //se l'utente in sessione possiede il servizio createUser...
            if (((List <Service>) request.getSession().getAttribute("services")).contains(createUser)) {


                this.datamodel.put("message", null);

                //setto l'utente in sessione
                this.datamodel.put("user", sessionManager.getUser(request));

                //setto i servizi a cui ha accesso l'utente
                this.datamodel.put("services", request.getSession().getAttribute("services"));


                //lancio il template di creazione utente
                TemplateController.process( "create_user.ftl", datamodel ,response, getServletContext() );

            } else {

                //lancio il messaggio di servizio non permesso
                this.processNotPermitted(request, response);
            }


            //se la sessione non e' valida
        }else{

            //setto la previous page e reindirizzo alla login
            createPreviousPageAndRedirectToLogin(request, response, "CreateUser");

        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pulisco messaggio
        datamodel.put("message",null);

        //rendo visibile il dao nel blocco catch
        UserDao userDao = null;

        try {

            //se sessione valida, uso hardValid perche questo processo implica un controllo di sicurezza
            if (sessionManager.isHardValid(request)) {

                //prendo il servizio createUser
                Service createUserService = this.getCreateUserService(request,response);

                //se all'utente e' permesso aggiungere utenti
                if (sessionManager.isPermissed(request, createUserService)) {

                    //raccolgo i dati dalla form
                    String emailForm = request.getParameter("email");

                    String passwordForm = request.getParameter("password");

                    //se i dati sono corretti
                    if( emailForm != null && emailForm != "" && utilityManager.isCorrectEmail(emailForm) &&
                            passwordForm != null && passwordForm != "" && passwordForm.length()>= utilityManager.getPasswordLength()) {

                        //inizializzo un UserDao
                        userDao = new UserDaoImpl(ds);
                        userDao.init();

                        //estraggo l'utente con tale email
                        User user = userDao.getUserByEmail(emailForm);

                        //se l'utente non e' gia presente nel database:
                        if (user == null || user.getId() <= 0) {

                            //faccio puntare user ad un utente che non sia null
                            user = userDao.getUser();

                            //setto l'user da inserire
                            user.setEmail(emailForm);
                            user.setPassword(utilityManager.sha1Encrypt(passwordForm));


                            //inserisco l'utente
                            int id =  userDao.storeUser(user);

                            //inserisco nel datamodel id dell'utenet appena creato
                            datamodel.put("idUserCreated", id);

                            //aggiungo un log di avvenuta creazioendi un utente
                            logManager.addLog(sessionManager.getUser(request),"USER CREATED: " + user.toStringForLog() + " BY: " + sessionManager.getUser(request).toStringForLog(), ds);

                            //se la mail e' gia presente nel database
                        } else {

                            //inserisco il messaggio di email gia' presente nel database
                            datamodel.put("message", "E-mail already in the database");

                            //setto l'utente in sessione
                            this.datamodel.put("user", sessionManager.getUser(request));

                            userDao.destroy();
                            userDao = null;

                            //setto i servizi a cui ha accesso l'utente
                            this.datamodel.put("services", request.getSession().getAttribute("services"));

                            //lancio la pagina di creazione dell'utente
                            TemplateController.process("create_user.ftl", datamodel, response, getServletContext());

                        }

                        userDao.destroy();
                        userDao = null;

                        //se i dati passati dalla form non sono corretti:
                    }else{
                        //inserisco il messaggio di dati non corretti
                        datamodel.put("message", "Past data is incorrect");

                        //setto l'utente in sessione
                        this.datamodel.put("user", sessionManager.getUser(request));

                        //setto i servizi a cui ha accesso l'utente
                        this.datamodel.put("services", request.getSession().getAttribute("services"));

                        //lancio la pagina di creazione dell'utente
                        TemplateController.process("create_user.ftl", datamodel, response, getServletContext());
                    }

                    //se all'utente non e' permesso aggiungere utenti
                } else {

                    //lancio il template della pagina not_permissed
                    this.processNotPermitted(request, response);
                }

                //se la sessione non e' valida
            }else{

                //setto la previous page e reindirizzo alla login
                createPreviousPageAndRedirectToLogin(request, response, "CreateUser");

            }

            //se va tutto a buon fine lancio la pagina di creazione dell'utente con il messaggio di avvenuta creazioen dell'utente

            //inserisco il messaggio utente creato
            datamodel.put("message", "User created");

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //setto i servizi a cui ha accesso l'utente
            this.datamodel.put("services", request.getSession().getAttribute("services"));

            //lancio la pagina di creazione dell'utente
            TemplateController.process("create_user.ftl", datamodel, response, getServletContext());

        }catch (SessionException | DaoException  e) {
            //in caso di dao exception ecc. lancio il template di errore
            this.processError(request, response);

        } catch (LogException e) {

            //rendo piu' facile il lavoro del garbage collector
            userDao = null;

            //inserisco il messaggio utente creato con messaggio di errore del log
            datamodel.put("message", "User created but insert log error ");

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //setto i servizi a cui ha accesso l'utente
            this.datamodel.put("services", request.getSession().getAttribute("services"));

            //lancio la pagina di creazione dell'utente
            TemplateController.process("create_user.ftl", datamodel, response, getServletContext());
        }
    }
}