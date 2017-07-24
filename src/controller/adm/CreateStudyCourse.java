package controller.adm;

import controller.BaseController;

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
public class CreateStudyCourse extends BaseController {

    @Resource(name = "jdbc/gdellapeProject")
    private static DataSource ds;

    private Map<String, Object> datamodel = new HashMap<>();

    /**
     * Lancia il template di creazione di un corso di studi
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Riceve i dati dalla form e inserisce il corso di studi dopo i dovuti controlli
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
