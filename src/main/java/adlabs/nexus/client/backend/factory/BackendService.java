package adlabs.nexus.client.backend.factory;

import adlabs.nexus.client.backend.api.account.AccountService;
import adlabs.nexus.client.backend.api.address.AddressService;
import adlabs.nexus.client.backend.api.asset.AssetService;
import adlabs.nexus.client.backend.api.block.BlockService;
import adlabs.nexus.client.backend.api.epoch.EpochService;
import adlabs.nexus.client.backend.api.pool.PoolService;
import adlabs.nexus.client.backend.api.transaction.TransactionService;

/** Entry point — exposes every Nexus Cardano resource service. */
public interface BackendService {

    AccountService getAccountService();

    AddressService getAddressService();

    AssetService getAssetService();

    BlockService getBlockService();

    EpochService getEpochService();

    PoolService getPoolService();

    TransactionService getTransactionService();
}
