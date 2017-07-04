package controller.utility;

import model.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public interface UtilityManager {

    boolean isCorrectEmail(String email);

    int getPasswordLength();

    /**
     * Data la stringa nameDescription, estrae il servizio corrispondente, se non esiste lo crea e lo restituisce
     * @param request
     * @param response
     * @param ds
     * @param nameService
     * @param descriptionService
     * @param datamodel
     * @param context
     * @return
     */
    Service getServiceAndCreate(HttpServletRequest request, HttpServletResponse response, DataSource ds, String nameService, String descriptionService, Map<String, Object> datamodel, ServletContext context);

}
