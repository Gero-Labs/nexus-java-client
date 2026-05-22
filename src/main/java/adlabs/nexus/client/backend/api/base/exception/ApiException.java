package adlabs.nexus.client.backend.api.base.exception;

/**
 * Thrown on transport or deserialization failure when calling Nexus.
 * HTTP-level non-2xx responses are returned as an unsuccessful {@code Result}, not thrown.
 */
public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
