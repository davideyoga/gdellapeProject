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

        //setto i servizi a cui ha accesso l'utente
        this.datamodel.put("services", request.getSession().getAttribute("services"));

        //lancio template di home del back office
        TemplateController.process("home_back_office.ftl", this.datamodel, response, getServletContext());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processTemplate(request, response);
    }
}
