package adlabs.nexus.client.backend.api.account.impl;

import adlabs.nexus.client.backend.api.account.AccountApi;
import adlabs.nexus.client.backend.api.account.AccountService;
import adlabs.nexus.client.backend.api.account.model.*;
import adlabs.nexus.client.backend.api.base.ApiUtil;
import adlabs.nexus.client.backend.api.base.Result;
import adlabs.nexus.client.backend.api.base.exception.ApiException;
import adlabs.nexus.client.util.Network;
import retrofit2.Retrofit;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    private final AccountApi api;

    public AccountServiceImpl(Retrofit retrofit) {
        this.api = retrofit.create(AccountApi.class);
    }

    @Override
    public Result<AccountInformation> getAccountInformation(Network network, String stakeAddress) throws ApiException {
        return ApiUtil.process(api.getAccountInfo(stakeAddress, network.queryValue()));
    }

    @Override
    public Result<List<AccountAddress>> getAccountAddresses(Network network, String stakeAddress) throws ApiException {
        return ApiUtil.process(api.getAccountAddresses(stakeAddress, network.queryValue()));
    }

    @Override
    public Result<List<AccountRewardsHistory>> getAccountRewards(Network network, String stakeAddress) throws ApiException {
        return ApiUtil.process(api.getAccountRewards(stakeAddress, network.queryValue()));
    }

    @Override
    public Result<List<AccountUtxo>> getAccountUtxos(Network network, String stakeAddress) throws ApiException {
        return ApiUtil.process(api.getAccountUtxos(stakeAddress, network.queryValue()));
    }

    @Override
    public Result<List<AccountAsset>> getAccountAssets(Network network, String stakeAddress,
                                                       int page, int pageSize) throws ApiException {
        return getAccountAssets(network, stakeAddress, null, page, pageSize);
    }

    @Override
    public Result<List<AccountAsset>> getAccountAssets(Network network, String stakeAddress, String policyId,
                                                       int page, int pageSize) throws ApiException {
        return ApiUtil.process(api.getAccountAssets(stakeAddress, network.queryValue(), policyId, page, pageSize));
    }

    @Override
    public Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                                   int fromBlockHeight) throws ApiException {
        // null order/to: leave the server defaults alone, so this overload behaves exactly
        // as it did before the parameters were exposed.
        return ApiUtil.process(
                api.getAccountTransactions(stakeAddress, network.queryValue(), fromBlockHeight, null, null));
    }

    @Override
    public Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                                   int fromBlockHeight, Integer toBlockHeight,
                                                                   String order) throws ApiException {
        return ApiUtil.process(
                api.getAccountTransactions(stakeAddress, network.queryValue(), fromBlockHeight, toBlockHeight, order));
    }
}
