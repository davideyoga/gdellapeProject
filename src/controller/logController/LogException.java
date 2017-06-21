package controller.logController;

/**
 * @Creator Davide Micarelli
 */
public class LogException extends Exception {

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (getCause()!=null?" ("+getCause().getMessage()+")":"");
    }

}
