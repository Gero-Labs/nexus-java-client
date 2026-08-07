package adlabs.nexus.client.backend.api.script;

import adlabs.nexus.client.backend.api.script.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ScriptApi {

    @GET("api/scripts/{scriptHash}")
    Call<ScriptDetail> getScriptByHash(@Path("scriptHash") String scriptHash, @Query("network") String network);

    @GET("api/scripts/datum/{datumHash}")
    Call<Datum> getDatumByHash(@Path("datumHash") String datumHash, @Query("network") String network);
}
