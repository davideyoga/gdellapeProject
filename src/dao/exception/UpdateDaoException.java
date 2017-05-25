package dao.exception;

/**
 * Created by davide on 25/05/17.
 */
public class UpdateDaoException extends DaoException {


    public UpdateDaoException(String message) {
        super(message);
    }

    public UpdateDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDaoException(Throwable cause) {
        super(cause);
    }
}


