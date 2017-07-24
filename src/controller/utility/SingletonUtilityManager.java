package controller.utility;

import dao.exception.DaoException;
import dao.implementation.ServiceDaoImpl;
import dao.interfaces.ServiceDao;
import model.Service;
import view.TemplateController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Davide Micarelli
 */
public class SingletonUtilityManager implements UtilityManager {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final int PASSWORD_LENGTH = 6;

    private static SingletonUtilityManager utilityManager;

    private SingletonUtilityManager(){}

    /**
     * Singleton constructor
     */
    public static SingletonUtilityManager getUtilityManager(){

        if(utilityManager == null){
            utilityManager = new SingletonUtilityManager();
        }

        return utilityManager;
    }


    /**
     * Controlla tramite regular expression la validita' della email
     * @param email
     * @return
     */
    @Override
    public boolean isCorrectEmail(String email) {

        if (email.matches(EMAIL_PATTERN)) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getPasswordLength() {
        return PASSWORD_LENGTH;
    }



}
