package adlabs.nexus.client.backend.api.script;

import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.script.model.*;
import adlabs.nexus.client.util.Network;

/** Cardano Plutus/native script and datum lookup endpoints. */
public interface ScriptService {

    Result<ScriptDetail> getScriptByHash(Network network, String scriptHash) throws ApiException;

    Result<Datum> getDatumByHash(Network network, String datumHash) throws ApiException;
}
