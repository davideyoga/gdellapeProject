package controller;

import controller.session.SessionManager;
import dao.exception.DaoException;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author  Davide Micarelli
 */
public class Login extends HttpServlet {

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
        TemplateController.process( "login.html", this.datamodel ,response, getServletContext() );
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

                    //distruggo l'attuale sessione
                    SessionManager.destroySession(request);

                    //inizializzo una nuova sessione
                    HttpSession session = request.getSession(true);

                    //insericso nella sessione i parametri base
                    SessionManager.initSession(request, user, ds);

                    //inserisco nella sessione gruppi e servizi appartenenti all'utente
                    SessionManager.getSessionWithGroupsAndService( request, ds);

                    //inserisco nel datamodel l'utente
                    this.datamodel.put("user", user);

                    //se esiste reinderizzo alla pagina precedente
                    if(this.previusPage != null){

                        response.sendRedirect(previusPage);
                    }

                    //lancio il template passandogli il datamodel contenente l'utente, reindirizzo alla home del back-office
                    TemplateController.process( "index_back_office.html", this.datamodel ,response, getServletContext() );

                // se l'utente non e' nel database
                }else processUnknown( request, response);

            } catch (DaoException e) {
                e.printStackTrace();
            }
        }else{
            //rilancio la pagina di login con message di errore "parametri errati"
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se richiesta get lancio il template di login
        TemplateController.process( "login_content.html", datamodel ,response, getServletContext() );
    }
}