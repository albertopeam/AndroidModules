package apa.components.data.cache;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.store.StoreAccountSource;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreAccountCacheDataSource implements StoreAccountSource{

    @Override
    public boolean store(AccountEntity accountEntity) {
        return false;
    }
}
