package controller.adm.user;

import controller.BaseController;
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
 * DA SISTEMARE: CONTROLLARE CHE TUTTI I CAMPI CHE VNEGONO USATI VENGANO EFFETTIVAMENTE PASSATI ALLA SERVLET
 *
 */
public class ProfileManagement extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    /**
     *
     * @param request
     * @param response
     * @return TORNA TRUE SE NUOVA PASSWORD E' CORRETTA, FALSE ALTRIMENTI
     * @throws ServletException
     * @throws IOException
     *
     * Testata e funzionante
     */
    private boolean ceckPostValue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String password = request.getParameter("password");

        //se password non nulla
        if(!password.equals(null) && !password.equals("")){

            System.out.println("password non nulla");

            //se password non nulla controllo che sia piu' lunga di 6 e uguale a ripetere password
            if(password.length()>5 && password.equals(request.getParameter("ripetere-password"))){

                System.out.println("password non nulla e corretta");

                return true;

                //se password errata
            }else{
                System.out.println("password non nulla ma scorretta");

                return false;
            }
            //se password nulla
        }else{
            System.out.println("password nulla");

            return false;
        }
    }

    /**
     * carica nel datamodel l'utente attuale e lancia il template
     * @param request
     * @param response
     */
    private void processTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {

            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            User user = userDao.getUserById(sessionManager.getUser(request).getId());
            user.setPassword(null);

            //inserisco utente del db nel datamodel
            this.datamodel.put("userCurrent", user);

            //inserisco utente del db (serve per il template)
            this.datamodel.put("user", user);

            userDao.destroy();
            userDao = null;

            //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
            TemplateController.process("user_profile_management.ftl", datamodel, response, getServletContext());

        } catch (DaoException e) {
            e.printStackTrace();

            this.processError(request, response);
        }//FINE BLOCCO CATCH
    }//FINE METODO


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione non valida, uso hardValid perche la modifica del profilo implica un controllo di sicurezza
        if(!sessionManager.isHardValid(request)){

            //setto la pagina precedentemente visualizzata e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request,response,"ProfileManagement");

        }else {//se la sessione e' valida...

            //carico l'utente nel templete e lancio il template
            processTemplate(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pulisco il messaggio
        datamodel.put("message",null);

        try {

            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            User newUser = userDao.getUser();
            User oldUser = userDao.getUserById(sessionManager.getUser(request).getId());

            userDao.destroy();

            //riempio con i dati provenienti dalla newUser
            newUser = this.getUserByForm(request, newUser, oldUser.getId());

            //se la sessione e' valida e abbastanza nuova
            if(sessionManager.isHardValid( request )){

                //se la nuova pass e' corretta
                if (this.ceckPostValue(request, response)){

                    //System.out.println("Password corretta: " + request.getParameter("password"));

                    //se password corretta prendo la nuova password, la cripto e la setto nell'utente
                    newUser.setPassword(utilityManager.sha1Encrypt(request.getParameter("password")));

                    //se pass non corretta mi prendo la password precedente
                }else{

                    //System.out.println("Setto la vecchia password: " + oldUser.getPassword());

                    //setto la password vecchia
                    newUser.setPassword(oldUser.getPassword());

                }

                //eseguo update
                userDao.init();

                userDao.storeUser(newUser);

                userDao.destroy();


                this.processTemplate(request, response);

            }else{//se la sessione non  e' valida o non abbastanza nuova

                //setto la redirect e lancio il template di login
                createPreviousPageAndRedirectToLogin(request,response,"ProfileManagement");
            }


        } catch (DaoException e) {
            e.printStackTrace();

            this.processError(request, response);
        }
    }
}
