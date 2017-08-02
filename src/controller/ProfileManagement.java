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
        this.datamodel.put("user", sessionManager.getUser(request));

        //se richiesta get lancio il template di profilo con i dati dell'utente in sessione
        TemplateController.process("profile.ftl", datamodel, response, getServletContext());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pulisco il messaggio
        datamodel.put("message",null);

        //se la sessione e' valida e abbastanza nuova
        if(sessionManager.isHardValid( request )){

            //inizializzo l'user da form per renderlo visibile nel blocco catch
            User userDaForm = null;

            //inizializzo il dao
            UserDao userDao = new UserDaoImpl(ds);
            try {
                userDao.init();

                //creo un user da riempire con i dati provenienti dalla form
                userDaForm = userDao.getUser();

                //estraggo l'utente dalla sessione
                User userInSessione = (User) request.getSession().getAttribute("user");

                /*
                    INIZIO ESTRAZIONE DATI DA RICHIESTA POST
                 */
                userDaForm.setId(userInSessione.getId());
                userDaForm.setSurname(request.getParameter("surname"));
                userDaForm.setName(request.getParameter("name"));
                userDaForm.setEmail(userInSessione.getEmail());
                //per castare la stringa in int
                String numberDaForm = request.getParameter("number");

                //se il numero estratto dalla form e' diverso da 0 e divero da ""
                if(numberDaForm != null && numberDaForm!="") {

                    long number = Long.parseLong(numberDaForm);

                    userDaForm.setNumber(number);


                }else{
                    userDaForm.setNumber(userInSessione.getNumber());
                }
                userDaForm.setCurriculum_ita(request.getParameter("curriculum_ita"));
                userDaForm.setCurriculum_eng(request.getParameter("curriculum_eng"));
                userDaForm.setReceprion_hours_ita(request.getParameter("receprion_hours_ita"));
                userDaForm.setReceprion_hours_eng(request.getParameter("receprion_hours_eng"));
                userDaForm.setPassword(request.getParameter("password"));

                /*
                    FINE ESTRAZIONE DATI DA RICHIESTA POST
                 */

                //se le password inserite sono diverse
                if(!userDaForm.getPassword().equals(request.getParameter("ripetere-password"))){

                    //lancio il template con messaggio di errore inerente
                    datamodel.put("message","Error, different password");
                    datamodel.put("user",userInSessione);
                    TemplateController.process("profile.ftl", datamodel, response, getServletContext());


                }else {//se le password inserite sono corrette:

                    //se le password sono < di 6 caratteri
                    if (userDaForm.getPassword().length() < 6) {

                        //lancio il template con messaggio di errore inerente
                        datamodel.put("message", "Error, The password is smaller than 6 characters");
                        datamodel.put("user", userInSessione);
                        TemplateController.process("profile.ftl", datamodel, response, getServletContext());


                        /*
                            SE I CONTROLLI SUI DATI DA RICHIESTA POST SONO ANDATI A BUON FINE
                         */
                    } else {

                        //controllo se i dati sono diversi da quelli in sessione (quindi quelli vecchi)
                        if (!userDaForm.equals(userInSessione)) {

                            //eseguo l'update dell'utente
                            userDao.storeUser(userDaForm);

                            //inserisco il messaggio di avvenuta modifica dei dati personali
                            datamodel.put("message", "Update Successful");

                            //inserisco un log nel db descrivendo cio' che e' successo
                            logManager.addLog(userDaForm, "USER WITH ID: " + userDaForm.getId() + " HAS CHANGE YOUR PERSONAL DATA.        " +
                                    "PREVIOUS USER: " + sessionManager.getUser(request) + "\n" +
                                    "NEW USER: " + userDaForm, ds);


                        }

                        //chiudo il dao
                        userDao.destroy();

                        //carico in sessione il nuovo utente (inserisco userDaForm, che sia uguale a prima o cambiato e' lui che vogliamo!!)
                        sessionManager.setUser(request, userDaForm);

                        //inserisco il nuovo utente nel datamodel
                        datamodel.put("user", sessionManager.getUser(request));

                        //lancio tameplate
                        TemplateController.process("profile.ftl", datamodel, response, getServletContext());
                    }
                }


            /*
                BLOCCO CATCH
             */
            } catch (DaoException e) {

                //in caso di dao exception lancio il template di errore
                TemplateController.process("error.ftl", datamodel,response,getServletContext());

            } catch (LogException e) {

                //rimuovo il precedente user dalla sessione
                request.getSession().removeAttribute("user");

                //carico il nuovo utente in sessione
                request.getSession().setAttribute("user", userDaForm);

                //chiudo il dao

                try {

                    userDao.destroy();

                } catch (DaoException e1) {
                    //in caso di dao exception lancio il template di errore
                    TemplateController.process("error.ftl", datamodel,response,getServletContext());
                }

                datamodel.put("message", "Error, not insert log");

                //lancio il template di profilo con i dati dell'utente in sessione ed il messaggio di errore del log
                TemplateController.process("profile.ftl", datamodel, response, getServletContext());

            }
            /*
                FINE BLOCCO CATCH
             */

        }else{//se la sessione non  e' valida o non abbastanza nuova

            //setto la redirect e lancio il template di login
            createPreviousPageAndRedirectToLogin(request,response,"ProfileManagement");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se sessione non valida, uso hardValid perche la modifica del profilo implica un controllo di sicurezza
        if(!sessionManager.isHardValid(request)){

            //setto la pagina precedentemente visualizzata e reindirizzo al login
            createPreviousPageAndRedirectToLogin(request,response,"ProfileManagement");

        }else {//se la sessione e' valida...

            //carico l'utente nel temaplete e lancio il template
            processTemplate(request, response);
        }
    }
}
