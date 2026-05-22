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
    public Result<List<AccountTransaction>> getAccountTransactions(Network network, String stakeAddress,
                                                                   int fromBlockHeight) throws ApiException {
        return ApiUtil.process(api.getAccountTransactions(stakeAddress, network.queryValue(), fromBlockHeight));
    }
}
