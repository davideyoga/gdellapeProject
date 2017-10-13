package controller.adm;

import controller.BaseController;
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
 * @author  Davide Micarelli
 */
public class Register extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();
    private String previusPage = null;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            TemplateController.process( "register.ftl", datamodel ,response, getServletContext() );
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //se richiesta get lancio il template di register
        TemplateController.process( "register.ftl", datamodel ,response, getServletContext() );
    }
}
