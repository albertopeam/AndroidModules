package apa.accessmodule.data.repository.store.sources;

import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.data.repository.store.StoreAccountSource;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreAccountDataSource implements StoreAccountSource{

    @Override
    public boolean store(AccountCloud accountCloud) {
        return false;
    }

}
