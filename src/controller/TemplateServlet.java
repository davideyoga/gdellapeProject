package controller;


import dao.implementation.UserDaoImpl;
import dao.interfaces.UserDao;
import model.User;
import view.TemplateController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Creator Davide Micarelli
 */
public class TemplateServlet extends HttpServlet {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {


        UserDao ud = new UserDaoImpl(ds);

        User user = ud.getUser();

        user.setEmail("gianni@gianni.gianni");

        Map<String, Object> datamodel = new HashMap<String, Object>();

        datamodel.put("user", user);

        TemplateController.process( "testFreemarker.html", datamodel ,response,getServletContext() );

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
