package controller;

import controller.sessionController.SessionException;
import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.UserDao;
import model.Groups;
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
 * @Creator Davide Micarelli
 */
public class ListUser extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Estrae tutti gli utenti, li inserisce nel datamodel e lancia il template della lista degli utenti
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //inizializzo i dao
            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();
            GroupsDao groupsDao = new GroupsDaoImpl(ds);
            groupsDao.init();

            Groups docente = groupsDao.getGroupsByName("Docente");

            //se il gruppo docenti non esiste
            if(docente == null){
                //lancio serlvet di errore
                response.sendRedirect("Error");
            }

            //estraggo gli utenti che corrispondono al gruppo dei docenti
            List<User> userList = userDao.getUserByGroups(docente);

            utilityManager.removePassword(userList);

            //carico la lista dei docenti nel datamodel
            datamodel.put("users", userList);

            //carico la lingua nel datamodel
            this.setLng(request, datamodel);

            //setto l'utente in sessione
            this.datamodel.put("user", sessionManager.getUser(request));

            //lancia il template appropriato alla lingua selezionata dall'utente
            this.processTemplate(request, response, "user_list", datamodel);

        } catch (DaoException e) {
            e.printStackTrace();

            //lancio messaggio di errore
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
