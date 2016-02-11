package apa.accessmodule.data.repository.store;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.cloud.AccountBoundaryToAccountEntityMapper;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.StoreAccountRepository;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreDataAccountRepository implements StoreAccountRepository{


    private StoreAccountSource cacheSource;
    private StoreAccountSource persistenceSource;
    private AccountBoundaryToAccountEntityMapper mapper;


    public StoreDataAccountRepository(StoreAccountSource cacheSource, StoreAccountSource persistenceSource, AccountBoundaryToAccountEntityMapper mapper) {
        this.cacheSource = cacheSource;
        this.persistenceSource = persistenceSource;
        this.mapper = mapper;
    }

    @Override
    public boolean store(AccountBoundary accountBoundary) {
        AccountEntity accountEntity = mapper.map(accountBoundary);
        boolean persisted = false;
        boolean cache = false;
        if (hasPersistence()){
            persisted = persistenceSource.store(accountEntity);
        }else{
            persisted = true;
        }
        if (hasCache()){
            cache = cacheSource.store(accountEntity);
        }else{
            cache = true;
        }
        return persisted && cache;
    }

    private boolean hasCache(){
        return cacheSource != null;
    }

    private boolean hasPersistence(){
        return persistenceSource != null;
    }
}
