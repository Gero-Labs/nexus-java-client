package adlabs.nexus.client.backend.api.address.impl;

import adlabs.nexus.client.backend.api.address.AddressApi;
import adlabs.nexus.client.backend.api.address.AddressService;
import adlabs.nexus.client.backend.api.address.model.*;
import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private final AddressApi api;

    public AddressServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(AddressApi.class);
    }

    @Override
    public Result<AddressInfo> getAddressInformation(Network network, String address) throws ApiException {
        return ApiUtil.process(api.getAddressInfo(address, network.queryValue()));
    }

    @Override
    public Result<List<AddressTransaction>> getAddressTransactions(Network network, String address,
                                                                   int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getAddressTransactions(address, network.queryValue(), page, pageSize));
    }

    @Override
    public Result<List<AddressUtxo>> getAddressUtxos(Network network, String address,
                                                     int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getAddressUtxos(address, network.queryValue(), page, pageSize));
    }

    @Override
    public Result<List<AddressUtxo>> getAddressUtxosByAsset(Network network, String address, String asset,
                                                            int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getAddressUtxosByAsset(address, asset, network.queryValue(), page, pageSize));
    }

    @Override
    public Result<TransactionHistoryResponse> getAddressTransactionHistory(Network network, String address,
                                                                           int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getAddressTransactionHistory(address, network.queryValue(), page, pageSize));
    }
}
