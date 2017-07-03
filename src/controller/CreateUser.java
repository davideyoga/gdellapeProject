package controller;

import controller.sessionController.SessionException;
import dao.exception.DaoException;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.ServiceDao;
import model.Service;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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


    private void processTemplate(HttpServletRequest request, HttpServletResponse response){

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){

        //se sessione non valida, uso hardValid perche questo processo implica un controllo di sicurezza
        if(!sessionManager.isHardValid(request)) {

            //inizializzo il dao per estrarre il servizio createUser
            ServiceDao serviceDao = new ServiceDaoImpl(ds);

            try {

                //inizializzo query ecc...
                serviceDao.init();

                //estraggo il servizio createUser
                Service createUser = serviceDao.getServiceByName("createUser");

                //se il servizio non e' presente nel database o non ha un id valido lo creo
                if (createUser == null && createUser.getId() <= 0) {

                    //faccio puntare createUser ad un servizio vuoto da riempire
                    createUser = serviceDao.getService();

                    //setto il servizio
                    createUser.setName("createUser");
                    createUser.setDescription("Service for create new user");

                    //inserisco il servizio nel database
                    serviceDao.storeService(createUser);
                }

                //se l'utente in sessione possiede il servizio createUser...
                if (((List <Service>) request.getSession().getAttribute("services")).contains(createUser)) {

                    //lancio il template di creazione utente
                    TemplateController.process( "create_user.ftl", datamodel ,response, getServletContext() );

                } else {

                    //lancio il messaggio di servizio non permesso
                    TemplateController.process( "not_permissed.ftl", datamodel ,response, getServletContext() );

                }


            } catch (DaoException e) {

                //reindirizzo alla pagina di errore

                e.printStackTrace();
            }


        //se la sessione non e' valida
        }else{

            //creo la sessione se non esiste e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            request.getSession(true);

            //inserisco nella sessione la pagina precedentemente visitata
            try {
                sessionManager.setPreviusPage(request, "CreateUser");

                //reindirizzo verso servlet di login
                response.sendRedirect("Login");

            } catch (SessionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        //controllo sulla sessione

        //raccolgo i dati dalla form

        //se i dati della form sono corretti effetto la insert

        //se i dati non sono corretti rimando un messaggio di errore

    }


}
