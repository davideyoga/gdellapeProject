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
 * @author Davide Micarelli
 */
public class UserProfile extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();


    /**
     * Estrae da richiesta GET la email, estrae l'utente con tale email e lo restituisce a video
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            User user = userDao.getUserByEmail(request.getParameter("email"));

            userDao.destroy();

            //se l'utente esiste nel sistema
            if(!(user == null) && user.getId()>0){

                //carico l'user nel datamodel
                datamodel.put("userCurrent", user);

                //carico la lingua nel datamodel
                this.setLng(request, datamodel);

                //setto l'utente in sessione
                this.datamodel.put("user", request.getSession().getAttribute("user"));

                System.out.println(datamodel.get("user"));

                //lancia il template appropriato alla lingua selezionata dall'utente
                this.processTemplate(request, response, "user_profile", datamodel);

            }else{//se l'utente non esiste nel sistema
                //lancio template di errore
                TemplateController.process("error.ftl", datamodel, response, getServletContext());
            }

        } catch (DaoException e) {
            //lancio template di errore
            TemplateController.process("error.ftl", datamodel, response, getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
