package controller.utility;

/**
 * @author  Davide Micarelli
 */
public class AccademicYearException extends Exception {

    public AccademicYearException(String message) {
        super(message);
    }

    public AccademicYearException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccademicYearException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (getCause()!=null?" ("+getCause().getMessage()+")":"");
    }

}
