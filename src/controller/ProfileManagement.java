package controller;

import controller.logController.LogException;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class ProfileManagement extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * carica nel datamodel l'utente in sessione e lancia il template
     * @param request
     * @param response
     */
    private void processTemplate(HttpServletRequest request, HttpServletResponse response){

        //inserisco l'user estratto dalla sessione da passare al template
        this.datamodel.put("user", request.getSession().getAttribute("user"));

        //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
        TemplateController.process("profile.ftl", datamodel, response, getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se la sessione e' valida e abbastanza nuova
        if(sessionManager.isHardValid( request )){

            //inizializzo il dao
            UserDao userDao = new UserDaoImpl(ds);
            try {
                userDao.init();

                //creo un user da riempire con i dati provenienti dalla form
                User userDaForm = userDao.getUser();

                //estraggo l'utente dalla sessione
                User user = (User) request.getSession().getAttribute("user");

                //creo un utente con i dati della form
                userDaForm.setId(user.getId());
                userDaForm.setSurname(request.getParameter("surname"));
                userDaForm.setName(request.getParameter("name"));
                userDaForm.setEmail(request.getParameter("email"));

                //per castare la stringa in int
                String numberDaForm = request.getParameter("number");

                //se il numero estratto dalla form e' diverso da 0 e divero da ""
                if(numberDaForm != null && numberDaForm!="") {

                    long number = Long.parseLong(numberDaForm);

                    userDaForm.setNumber(number);


                }else{
                    userDaForm.setNumber(user.getNumber());
                }

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
                    logManager.addLog(userDaForm, "User with id: " + userDaForm.getId() + " has change your personal data", ds);

                    //rimuovo il precedente user dalla sessione
                    request.getSession().removeAttribute("user");

                    //carico il nuovo utente in sessione
                    request.getSession().setAttribute("user", userDaForm);

                }

                //chiudo il dao
                userDao.destroy();

                //lancio il template(con i nuovi dati se sono cambiati, altrimenti con quelli vecchi)
                processTemplate(request, response);

            } catch (DaoException e) {
                e.printStackTrace();
            } catch (LogException e) {
                e.printStackTrace();
            }


        }else{//se la sessione non  e' valida o non abbastanza nuova

            //non serve distruggere la sessione,
            //SessionManager.destroySession(request);

            //creo o prendo la sessione eistente e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
            //in modo di poterlo reindirizzare dopo aver rieffettuato il login
            HttpSession newSession = request.getSession(true);

            try {

                sessionManager.setPreviusPage(request, "ProfileManagement");

            } catch (SessionException e) {
                e.printStackTrace();
            }

            //lancio il template di login
            TemplateController.process( "profile.ftl", datamodel ,response, getServletContext() );
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione non valida, uso hardValid perche la modifica del profilo implica un controllo di sicurezza
        if(!sessionManager.isHardValid(request)){

            try {

                //ditruggo la sessione se esiste in quanto non e' valida
                //sessionManager.destroySession(request);

                //creo la sessione e carico la pagina in cui si trovava l'utente prima di essere reindirizzato al login
                //in modo di poterlo reindirizzare dopo aver rieffettuato il login
                request.getSession(true);

                //inserisco nella sessione la pagina precedentemente visitata
                sessionManager.setPreviusPage(request, "ProfileManagement");

                //reindirizzo verso servlet di login
                response.sendRedirect("Login");

            } catch (SessionException e) {
                e.printStackTrace();
            }

        }else {//se la sessione e' valida...

            //carico l'utente nel temaplete e lancio il template
            processTemplate(request, response);
        }
    }
}
