package dao.interfaces;

import dao.data.DaoData;
import dao.exception.DaoException;
import dao.exception.SelectDaoException;
import model.Log;
import model.User;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author  Davide Micarelli
 */
public interface LogDao extends DaoData {

    public Log getLog();

    public void insertLog(Log log) throws DaoException;

    public Log getLogById( int idLog) throws DaoException;

    public List<Log> getLogByUser(User user) throws DaoException;

    public List<Log> getLogByDate(Timestamp date) throws DaoException;

    public void deleteLog( Log log) throws DaoException;

    public Log generateLog(ResultSet rs) throws DaoException;

    List<Log> getAllLog() throws DaoException;
}
