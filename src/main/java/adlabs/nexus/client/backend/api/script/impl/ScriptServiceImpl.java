package adlabs.nexus.client.backend.api.script.impl;

import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.backend.api.script.ScriptApi;
import adlabs.nexus.client.backend.api.script.ScriptService;
import adlabs.nexus.client.backend.api.script.model.*;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

public class ScriptServiceImpl implements ScriptService {

    private final ScriptApi api;

    public ScriptServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(ScriptApi.class);
    }

    @Override
    public Result<ScriptDetail> getScriptByHash(Network network, String scriptHash) throws ApiException {
        return ApiUtil.process(api.getScriptByHash(scriptHash, network.queryValue()));
    }

    @Override
    public Result<Datum> getDatumByHash(Network network, String datumHash) throws ApiException {
        return ApiUtil.process(api.getDatumByHash(datumHash, network.queryValue()));
    }
}
