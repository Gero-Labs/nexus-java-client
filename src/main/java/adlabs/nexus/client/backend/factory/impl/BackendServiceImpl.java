package adlabs.nexus.client.backend.factory.impl;

import adlabs.nexus.client.backend.api.account.AccountService;
import adlabs.nexus.client.backend.api.account.impl.AccountServiceImpl;
import adlabs.nexus.client.backend.api.address.AddressService;
import adlabs.nexus.client.backend.api.address.impl.AddressServiceImpl;
import adlabs.nexus.client.backend.api.asset.AssetService;
import adlabs.nexus.client.backend.api.asset.impl.AssetServiceImpl;
import adlabs.nexus.client.backend.api.block.BlockService;
import adlabs.nexus.client.backend.api.block.impl.BlockServiceImpl;
import adlabs.nexus.client.backend.api.epoch.EpochService;
import adlabs.nexus.client.backend.api.epoch.impl.EpochServiceImpl;
import adlabs.nexus.client.backend.api.marketdata.MarketDataService;
import adlabs.nexus.client.backend.api.marketdata.impl.MarketDataServiceImpl;
import adlabs.nexus.client.backend.api.metadata.MetadataService;
import adlabs.nexus.client.backend.api.metadata.impl.MetadataServiceImpl;
import adlabs.nexus.client.backend.api.network.NetworkService;
import adlabs.nexus.client.backend.api.network.impl.NetworkServiceImpl;
import adlabs.nexus.client.backend.api.pool.PoolService;
import adlabs.nexus.client.backend.api.pool.impl.PoolServiceImpl;
import adlabs.nexus.client.backend.api.script.ScriptService;
import adlabs.nexus.client.backend.api.script.impl.ScriptServiceImpl;
import adlabs.nexus.client.backend.api.transaction.TransactionService;
import adlabs.nexus.client.backend.api.transaction.impl.TransactionServiceImpl;
import adlabs.nexus.client.backend.factory.BackendService;
import retrofit2.Retrofit;

public class BackendServiceImpl implements BackendService {

    private final AccountService accountService;
    private final AddressService addressService;
    private final AssetService assetService;
    private final BlockService blockService;
    private final EpochService epochService;
    private final MarketDataService marketDataService;
    private final MetadataService metadataService;
    private final NetworkService networkService;
    private final PoolService poolService;
    private final ScriptService scriptService;
    private final TransactionService transactionService;

    public BackendServiceImpl(Retrofit retrofit) {
        this.accountService = new AccountServiceImpl(retrofit);
        this.addressService = new AddressServiceImpl(retrofit);
        this.assetService = new AssetServiceImpl(retrofit);
        this.blockService = new BlockServiceImpl(retrofit);
        this.epochService = new EpochServiceImpl(retrofit);
        this.marketDataService = new MarketDataServiceImpl(retrofit);
        this.metadataService = new MetadataServiceImpl(retrofit);
        this.networkService = new NetworkServiceImpl(retrofit);
        this.poolService = new PoolServiceImpl(retrofit);
        this.scriptService = new ScriptServiceImpl(retrofit);
        this.transactionService = new TransactionServiceImpl(retrofit);
    }

    @Override
    public AccountService getAccountService() {
        return accountService;
    }

    @Override
    public AddressService getAddressService() {
        return addressService;
    }

    @Override
    public AssetService getAssetService() {
        return assetService;
    }

    @Override
    public BlockService getBlockService() {
        return blockService;
    }

    @Override
    public EpochService getEpochService() {
        return epochService;
    }

    @Override
    public MarketDataService getMarketDataService() {
        return marketDataService;
    }

    @Override
    public MetadataService getMetadataService() {
        return metadataService;
    }

    @Override
    public NetworkService getNetworkService() {
        return networkService;
    }

    @Override
    public PoolService getPoolService() {
        return poolService;
    }

    @Override
    public ScriptService getScriptService() {
        return scriptService;
    }

    @Override
    public TransactionService getTransactionService() {
        return transactionService;
    }
}
