package by.task.telephoneexchange.pool;

/**
 * ConnectionPoolException.
 * Date: 12/21/2020
 *
 * @author Anastasiya Bezmen
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
