package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class DeleteDaoException extends DaoException {

    public DeleteDaoException(String message) {
        super(message);
    }

    public DeleteDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteDaoException(Throwable cause) {
        super(cause);
    }
}
