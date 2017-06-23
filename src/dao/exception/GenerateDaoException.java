package dao.exception;

/**
 * @Creator Davide Micarelli
 */
public class GenerateDaoException extends DaoException {

    public GenerateDaoException(String message) {
        super(message);
    }

    public GenerateDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenerateDaoException(Throwable cause) {
        super(cause);
    }
}
