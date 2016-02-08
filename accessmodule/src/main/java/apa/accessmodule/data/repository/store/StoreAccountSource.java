package apa.accessmodule.data.repository.store;

import apa.accessmodule.data.model.entity.AccountEntity;

/**
 * Created by alberto on 6/2/16.
 */
public interface StoreAccountSource {
    boolean store(AccountEntity accountEntity);
}
