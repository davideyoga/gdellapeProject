package controller.logManager;

import dao.exception.DaoException;
import dao.implementation.LogDaoImpl;
import dao.interfaces.LogDao;
import model.Log;
import model.User;

import javax.sql.DataSource;

/**
 * @author  Davide Micarelli
 */
public class LogManager {

    /**
     * Inserisce il log con l'id dell'utente passato ed la descrizione del log
     * @param user utente da coi estraggo l'id
     * @param description descrizione del log
     * @param ds datasource per inizializzare il dao
     * @throws LogException
     */
    public static void addLog(User user, String description, DataSource ds) throws LogException {

        //creo il dao
        LogDao logDao = new LogDaoImpl(ds);
        try {

            //inizializzo il dao
            logDao.init();

            //inizializzo il log da inserire nel db
            Log log = logDao.getLog();

            //setto i parametri del log
            log.setIdUser( user.getId() );
            log.setDescription( description );

            //inserisco il log nel database
            logDao.insertLog( log );

            logDao.destroy();

        } catch (DaoException e) {

            throw new LogException("Error addLog", e);
        }
    }

}
