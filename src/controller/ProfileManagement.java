package controller;

import controller.logManager.LogException;
import controller.logManager.LogManager;
import controller.session.SessionManager;
import dao.exception.DaoException;
import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
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
 * @Creator Davide Micarelli
 */

public class ProfileManagement extends HttpServlet {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //non serve estrarre la sessione in quanto si trova nella request
        //HttpSession session = request.getSession(false);

        //se la sessione e' valida e abbastanza nuova
        if(SessionManager.isHardValid( request )){

            UserDao userDao = new UserDaoImpl(ds);
            try {
                userDao.init();


                //creo un user da riempire con i dati provenienti dalla form
                User userDaForm = userDao.getUser();

                //estraggo l'utente dalla sessione
                User user = (User) request.getSession().getAttribute("user");

                //creo un utente con i dati della form
                userDaForm.setSurname(request.getParameter("surname"));
                userDaForm.setName(request.getParameter("name"));
                userDaForm.setEmail(request.getParameter("email"));
                userDaForm.setNumber(Integer.parseInt(request.getParameter("number")));
                userDaForm.setCurriculum_ita(request.getParameter("curriculum_ita"));
                userDaForm.setCurriculum_eng(request.getParameter("curriculum_eng"));
                userDaForm.setReceprion_hours_ita(request.getParameter("receprion_hours_ita"));
                userDaForm.setReceprion_hours_eng(request.getParameter("receprion_hours_eng"));
                userDaForm.setPassword(request.getParameter("password"));

                //controllo se i dati sono diversi da quelli in sessione (quindi quelli vecchi)
                if (!userDaForm.equals(user)) {

                    //inserisco l'id dell'utente
                    userDaForm.setId(user.getId());

                    //eseguo l'update dell'utente
                    userDao.storeUser(userDaForm);

                    //inserisco un log nel db descrivendo cio' che e' successo
                    LogManager.addLog(userDaForm, "User with id: " + userDaForm.getId() + " has change your personal date", ds);

                }

                //chiudo il dao
                userDao.destroy();

            } catch (DaoException e) {
                e.printStackTrace();
            } catch (LogException e) {
                e.printStackTrace();
            }


        }else{//se la sessione non  e' valida e abbastanza nuova

            //non serve distruggere la sessione,
            //SessionManager.destroySession(request);

            //creo la sessione e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("previus_page", "ProfileManagement");

            //lancio il template di login
            TemplateController.process( "profile.html", datamodel ,response, getServletContext() );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione non valida
        if(!SessionManager.isValid(request)){;

            //ditruggo la sessione
            SessionManager.destroySession(request);

            //creo la sessione e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("previus_page", "ProfileManagement");

            //reindirizzo verso pagina di login
            response.sendRedirect("Login");
        }

        //estraggo la sessione se esiste
        HttpSession session = request.getSession();

        //inserisco l'user estratto dalla sessione da passate al template
        this.datamodel.put("user", session.getAttribute("user"));

        //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
        TemplateController.process( "profile.html", datamodel ,response, getServletContext() );

    }
}
