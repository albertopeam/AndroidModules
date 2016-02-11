package apa.components.data.repository.sources;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.store.StoreAccountSource;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreAccountPersistenceDataSource implements StoreAccountSource{


    @Override
    public boolean store(AccountEntity accountEntity) {
        return false;
    }
}
