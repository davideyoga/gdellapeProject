package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class InitDaoException extends DaoException {

    public InitDaoException(String message) {
        super(message);
    }

    public InitDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitDaoException(Throwable cause) {
        super(cause);
    }
}
