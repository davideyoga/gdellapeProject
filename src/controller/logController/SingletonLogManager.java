package controller.logController;

import dao.exception.DaoException;
import dao.implementation.LogDaoImpl;
import dao.interfaces.LogDao;
import model.Log;
import model.User;

import javax.sql.DataSource;
import java.sql.Timestamp;



/**
 * @author  Davide Micarelli
 */
public class SingletonLogManager implements controller.logController.LogManager {

    private static SingletonLogManager logManager;

    private SingletonLogManager(){}

    public static SingletonLogManager getLogManager(){

        if(logManager == null) logManager = new SingletonLogManager();

        return logManager;
    }


    /**
     * Inserisce il log con l'id dell'utente passato ed la descrizione del log
     * @param user utente da coi estraggo l'id
     * @param description descrizione del log
     * @param ds datasource per inizializzare il dao
     * @throws LogException
     */
    public void addLog(User user, String description, DataSource ds) throws LogException {

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
            log.setDate(new Timestamp(System.currentTimeMillis()));

            //Timestamp.valueOf(request.getParameter("date")

            //inserisco il log nel database
            logDao.insertLog( log );

            logDao.destroy();

        } catch (DaoException e) {

            throw new LogException("Error addLog", e);
        }
    }

}
