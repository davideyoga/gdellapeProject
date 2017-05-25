package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class DestroyDaoException extends DaoException {
    public DestroyDaoException(String message) {
        super(message);
    }

    public DestroyDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DestroyDaoException(Throwable cause) {
        super(cause);
    }
}
