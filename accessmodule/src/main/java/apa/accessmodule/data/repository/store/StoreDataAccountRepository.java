package apa.accessmodule.data.repository.store;

import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.StoreAccountRepository;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreDataAccountRepository implements StoreAccountRepository{


    private StoreAccountSource storeAccountSource;


    public StoreDataAccountRepository(StoreAccountSource storeAccountSource) {
        this.storeAccountSource = storeAccountSource;
    }

    @Override
    public boolean store(AccountBoundary accountBoundary) {
        return false;
    }
}
