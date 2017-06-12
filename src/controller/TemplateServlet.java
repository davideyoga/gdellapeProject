package controller;


import dao.exception.DaoException;
import dao.implementation.GroupsDaoImpl;
import dao.implementation.ServiceDaoImpl;
import dao.implementation.UserDaoImpl;
import dao.interfaces.GroupsDao;
import dao.interfaces.ServiceDao;
import dao.interfaces.UserDao;
import model.Groups;
import model.Service;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */
public class TemplateServlet extends HttpServlet {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {



        UserDao userDao = new UserDaoImpl(ds);
        GroupsDao groupsDao = new GroupsDaoImpl(ds);


        try {

            userDao.init();
            groupsDao.init();

            Groups groups = groupsDao.getGroupsById(1);

            System.out.print(userDao.getUserByGroups(groups));

            System.out.print("numero di elementi nella lista: " + userDao.getUserByGroups(groups).size() );


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
