package exceptions;

/**
 * The type Service exception.
 *
 * @author Vedad
 */
public class ServiceException extends Exception {
    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Exception e) {

    }
}
