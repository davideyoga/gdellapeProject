package dao.implementation;

import dao.data.DaoDataMySQLImpl;
import dao.exception.*;
import dao.interfaces.LogDao;
import model.Log;
import model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static dao.security.DaoSecurity.addSlashes;
import static dao.security.DaoSecurity.stripSlashes;


/**
 * @Creator Davide Micarelli
 */
public class LogDaoImpl extends DaoDataMySQLImpl implements LogDao {

    private PreparedStatement insertLog,
                        selectLogById,
                        selectLogByUserId,
                        selectLogByDate,
                        deleteLogById;


    public LogDaoImpl(DataSource datasource) {
        super(datasource);
    }

    /**
     * iniaizlizzo connessione e query
     * @throws DaoException
     */
    public void init() throws DaoException{


        try {

            super.init();

            this.insertLog = connection.prepareStatement("INSERT INTO log" +
                    "                                               VALUES (NULL, ?, ?, ?)");

            this.selectLogById = connection.prepareStatement("SELECT *" +
                    "                                              FROM log" +
                    "                                               WHERE id=?");

            this.selectLogByUserId = connection.prepareStatement("SELECT *" +
                    "                                                FROM log" +
                    "                                                WHERE user_id=?");

            this.selectLogByDate = connection.prepareStatement("SELECT *" +
                    "                                                FROM log" +
                    "                                                WHERE date=?");

            this.deleteLogById = connection.prepareStatement("DELETE FROM log" +
                    "                                               WHERE id=?");


        } catch (SQLException e) {
            throw new InitDaoException("Error initializing log dao", e);
        }

    }

    /**
     * ritorna Log vuoto
     * @return
     */
    @Override
    public Log getLog() {
        return new Log(this);
    }

    /**
     * Inserisce il Log passato nel db
     * @param log da inserire nel db
     * @throws DaoException
     */
    @Override
    public void insertLog( Log log) throws DaoException {

        try {

            this.insertLog.setInt(1, log.getIdUser());
            this.insertLog.setTimestamp(2, log.getDate());
            this.insertLog.setString(3, addSlashes(log.getDescription()));

            this.insertLog.executeUpdate();

        } catch (SQLException e) {
            throw new InsertDaoException("Error insert LogDao",e);
        }

    }

    /**
     * torna il Log nel db con id = idLog
     * @param idLog id del Log
     * @return Log con id = idLog
     * @throws DaoException
     */
    @Override
    public Log getLogById(int idLog) throws DaoException {

        try {

            this.selectLogById.setInt(1,idLog);

            ResultSet rs = this.selectLogById.executeQuery();

            return this.generateLog( rs );

        } catch (SQLException e) {
            throw new SelectDaoException("Error getLogById", e);
        }
    }

    /**
     * Torna lista di Log collegati ad un dato utente
     * @param user con log collegati
     * @return Lista di log collegati all'User user
     * @throws DaoException
     */
    @Override
    public List<Log> getLogByUser(User user) throws DaoException {

        List<Log> logList = new ArrayList<>();

        try {

            this.selectLogByUserId.setInt(1,user.getId());

            ResultSet rs = this.selectLogByUserId.executeQuery();

            //ciclo sul risultato
            while (rs.next()) {

                logList.add(this.generateLog(rs)); // aggiungo alla lista il log della tupla di rs attuale

                /*
                    Log tempLog = this.generateLog( rs );
                    logList.add( tempLog );
                 */
            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getLogByUserId", e);
        }

        return logList;
    }

    /**
     * Torna lista di Log in data date
     * @param date data di cui si vogliono i log
     * @return Lista di log in data = date
     * @throws DaoException
     */
    @Override
    public List<Log> getLogByDate(Timestamp date) throws DaoException {

        List<Log> logList = new ArrayList<>();

        try {

            this.selectLogByDate.setTimestamp(1,date);

            ResultSet rs = this.selectLogByDate.executeQuery();

            while (rs.next()) {

                logList.add(this.generateLog(rs));
            }

        } catch (SQLException e) {
            throw new SelectDaoException("Error getLogByDate", e);
        }

        return logList;
    }

    /**
     * Cancella dal db il log passato
     * @param log da cancellare dal db
     * @throws DaoException
     */
    @Override
    public void deleteLog(Log log) throws DaoException {

        try {

            this.deleteLogById.setInt(1,log.getId());

            this.deleteLogById.executeUpdate();

        } catch (SQLException e) {
            throw new DeleteDaoException("Error deleteLog", e);
        }
    }

    /**
     * Genera un Log dal risultato della query passata
     * @param rs risultato della query da cui si estraggono i dati del log
     * @return
     * @throws DaoException
     */
    @Override
    public Log generateLog(ResultSet rs) throws DaoException {

        Log log = this.getLog(); // creo il log da restituire

        try {

            log.setId(rs.getInt("id")); // inserisco
            log.setIdUser(rs.getInt("user_id"));
            log.setDate(rs.getTimestamp("date"));
            log.setDescription(stripSlashes(rs.getString("description")));

        } catch (SQLException e) {
            throw new SelectDaoException("Error generateLog", e );
        }

        return log;
    }
}
