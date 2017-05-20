package dao.data;


import dao.exception.DaoException;

/**
 * 
 * @author Davide Micarelli
 *
 */
public interface DaoData extends AutoCloseable{
	
    void init() throws DaoException;

    void destroy() throws DaoException;
    
}