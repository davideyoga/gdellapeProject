package dao.data;

import dao.exception.DaoException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author Davide Micarelli
 * This classes is a base Connection with connection pooling 
 *
 */
public class DaoDataMySQLImpl implements DaoData{
	
	private DataSource datasource;
	protected Connection connection;

	public DaoDataMySQLImpl( DataSource datasource ){
        super();
        this.datasource = datasource;
        this.connection = null;
	}
	
	@Override
	public void init() throws DaoException {
        
		try {

			//InitialContext ctx = new InitialContext();

			//DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/gdellapeProject");

			connection = this.datasource.getConnection();

        } catch (SQLException e) {
            throw new DaoException("Error: db connection failed", e);
        }
		
	}
	

	@Override
	public void destroy() throws DaoException {
		try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new DaoException("Error: shutdown failed connection", e);
        }
		
	}
	
	@Override
	public void close() throws Exception {
		destroy();
	}

	
}