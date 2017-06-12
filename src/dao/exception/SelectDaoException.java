package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class SelectDaoException extends DaoException {


    public SelectDaoException(String message) {
        super(message);
    }

    public SelectDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectDaoException(Throwable cause) {
        super(cause);
    }
}
