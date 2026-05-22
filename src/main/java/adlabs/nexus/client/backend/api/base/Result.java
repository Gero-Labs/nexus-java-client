package adlabs.nexus.client.backend.api.base;

/**
 * Wraps a Nexus API call outcome.
 *
 * @param <T> response payload type
 */
public class Result<T> {

    private final boolean successful;
    private final int code;
    private final String response;
    private final T value;

    private Result(boolean successful, int code, String response, T value) {
        this.successful = successful;
        this.code = code;
        this.response = response;
        this.value = value;
    }

    public static <T> Result<T> success(int code, T value) {
        return new Result<>(true, code, "OK", value);
    }

    public static <T> Result<T> error(int code, String response) {
        return new Result<>(false, code, response, null);
    }

    /** True when Nexus returned a 2xx status. */
    public boolean isSuccessful() {
        return successful;
    }

    /** HTTP status code. */
    public int getCode() {
        return code;
    }

    /** "OK" on success, or the raw error body on failure. */
    public String getResponse() {
        return response;
    }

    /** Deserialized payload on success, {@code null} on failure. */
    public T getValue() {
        return value;
    }
}
