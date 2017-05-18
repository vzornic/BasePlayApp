package exceptions;

/**
 * The type Repository exception.
 *
 * @author Vedad
 */
public class RepositoryException extends Exception {

    /**
     * Instantiates a new Repository exception.
     *
     * @param message the message
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
