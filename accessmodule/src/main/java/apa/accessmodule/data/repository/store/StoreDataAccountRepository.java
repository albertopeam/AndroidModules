package apa.accessmodule.data.repository.store;

import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.StoreAccountRepository;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreDataAccountRepository implements StoreAccountRepository{


    private StoreAccountSource cacheSource;
    private StoreAccountSource persistenceSource;


    public StoreDataAccountRepository(StoreAccountSource cacheSource, StoreAccountSource persistenceSource) {
        this.cacheSource = cacheSource;
        this.persistenceSource = persistenceSource;
    }

    @Override
    public boolean store(AccountBoundary accountBoundary) {
        //Store inline. both providers
        return false;
    }
}
