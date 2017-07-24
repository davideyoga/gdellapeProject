package controller.utility;

import model.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public interface UtilityManager {

    boolean isCorrectEmail(String email);

    int getPasswordLength();


}
