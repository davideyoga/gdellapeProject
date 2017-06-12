package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class InsertDaoException extends DaoException {

    public InsertDaoException(String message) {
        super(message);
    }

    public InsertDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertDaoException(Throwable cause) {
        super(cause);
    }
}
