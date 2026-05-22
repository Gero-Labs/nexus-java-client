package adlabs.nexus.client.backend.api.base;

import adlabs.nexus.client.backend.api.base.exception.ApiException;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/** Executes a Retrofit {@link Call} and converts the outcome into a {@link Result}. */
public final class ApiUtil {

    private ApiUtil() {
    }

    public static <T> Result<T> process(Call<T> call) throws ApiException {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return Result.success(response.code(), response.body());
            }
            String errorBody = "";
            if (response.errorBody() != null) {
                errorBody = response.errorBody().string();
            }
            return Result.error(response.code(), errorBody);
        } catch (IOException e) {
            throw new ApiException("Nexus request failed: " + e.getMessage(), e);
        }
    }
}
