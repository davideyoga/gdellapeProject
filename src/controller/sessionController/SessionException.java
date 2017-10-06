package controller.sessionController;

/**
 * @Creator Davide Micarelli
 */
public class SessionException extends Exception {

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SessionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (getCause()!=null?" ("+getCause().getMessage()+")":"");
    }

}
