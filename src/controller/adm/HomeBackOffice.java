package controller.adm;

import controller.BaseController;
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
 * @Creator Davide Micarelli
 */
public class HomeBackOffice extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();
    private String previusPage = null;


    protected void processTemplate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //setto l'utente in sessione
        this.datamodel.put("user", sessionManager.getUser(request));

        //lancio template di home del back office
        TemplateController.process("login.ftl", this.datamodel, response, getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
