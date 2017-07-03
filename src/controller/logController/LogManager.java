package controller.logController;

import model.User;

import javax.sql.DataSource;

/**
 * @author Davide Micarelli
 */
public interface LogManager {

    void addLog(User user, String description, DataSource ds) throws LogException;

}
