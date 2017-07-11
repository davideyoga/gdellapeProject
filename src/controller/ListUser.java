package controller;

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

            UserDao userDao = new UserDaoImpl(ds);
            userDao.init();

            List<User> userList = userDao.getUsers();

            //carico l'user nel datamodel e lancio il template
            datamodel.put("users", userList);
            TemplateController.process("user_list.ftl", datamodel, response, getServletContext());


        } catch (DaoException e) {
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
