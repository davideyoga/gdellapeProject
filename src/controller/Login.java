package controller;

import controller.sessionController.SessionException;
import dao.exception.DaoException;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  Davide Micarelli
 */
public class Login extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();
    private String previusPage = null;

    /**
     * processo nel caso in email e password non corrispondano ad un utente nel database
     * @param request
     * @param response
     */
    protected  void processUnknown(HttpServletRequest request, HttpServletResponse response){

        //inserisco il messaggio di errore nel datamodel
        this.datamodel.put( "message", "email and password not match" );

        //lancio la pagina di login
        TemplateController.process( "login.ftl", this.datamodel ,response, getServletContext() );
    }

    /**
     * Prende i parametri post dalla form di login e ne controlla i dati, se corripsondono
     * ad un utente nel database passa alla pagina di back office
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //se i parametri passati sono corretti
        if( email != null && password != null && !email.equals("") && !password.equals("") ){

            //inizializzo un dao per estrarre l'utente collegato con questa email e password
            UserDao userDao = new UserDaoImpl(ds);
            try {

                //inizializzo il dao
                userDao.init();
                //estraggo l'utente con email e password
                User user = userDao.getUserByEmailPassword(email, password);

                //se l'utente e' nel database con tali email e password
                if( user != null){

                    /*
                        estraggo dalla sessione la pagina precedente al reindirizzamento
                        in modo da poter reindirizzare l'utente alla pagina precedente (se esiste),
                        utile nel caso in cui possiedo una sessione vecchia e voglio accedere
                        a servizi di grande importanza (ad esempio cambio passwrod ecc...)
                    */
                    this.previusPage = (String) request.getSession().getAttribute("previus_page");

                    //prima di eliminare la vecchia sessione mi prendo la pagina precedentemente visitata
                    String previousPage = sessionManager.getPreviusPage(request);

                    //distruggo l'attuale sessione
                    sessionManager.destroySession(request);

                    //inizializzo una nuova sessione
                    sessionManager.initSession(request, user, ds);

                    //inserisco nella sessione gruppi e servizi appartenenti all'utente
                    sessionManager.getSessionWithGroupsAndService( request, ds);

                    //inserisco nel datamodel l'utente
                    this.datamodel.put("user", user);

                    System.out.println("previousPage prima dell'if: " + previousPage);

                    //se esiste reinderizzo alla pagina precedente
                    if(previousPage != null && previousPage != ""){

                        System.out.println("entrato nell if con previousPage:" + previousPage);

                        //rendo null la pagina precedente prima di reindirizzare
                        sessionManager.setPreviusPage(request, null);

                        //reindirizzo alla servlet visitata precedentemente
                        response.sendRedirect(previousPage);

                    }else {

                        System.out.println("non entrato enn'if con previous page");

                        //lancio il template passandogli il datamodel contenente l'utente, reindirizzo alla home del back-office
                        TemplateController.process("home_back_office.ftl", this.datamodel, response, getServletContext());
                    }
                // se l'utente non e' nel database
                }else processUnknown( request, response);

            } catch (DaoException e) {
                e.printStackTrace();
            } catch (SessionException e) {
                e.printStackTrace();
            }
        }else{
            //rilancio la pagina di login con message di errore "parametri errati"
            this.datamodel.put("message","incorrect parameters");

            //lancio il template di login
            TemplateController.process( "login.ftl", datamodel ,response, getServletContext() );
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se richiesta get lancio il template di login
        TemplateController.process( "login.ftl", datamodel ,response, getServletContext() );
    }
}
