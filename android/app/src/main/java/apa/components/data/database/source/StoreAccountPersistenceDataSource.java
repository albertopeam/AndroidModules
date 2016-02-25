package apa.components.data.database.source;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.store.StoreAccountSource;
import apa.components.data.database.AccountTable;
import apa.components.data.database.mapper.AccountPersistenceMapper;

/**
 * Created by alberto on 6/2/16.
 */
public class StoreAccountPersistenceDataSource implements StoreAccountSource{

    private AccountPersistenceMapper mapper;


    public StoreAccountPersistenceDataSource(AccountPersistenceMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public boolean store(AccountEntity accountEntity) {
        AccountTable accountTable = mapper.map(accountEntity);
        return trySave(accountTable);
    }

    private boolean trySave(AccountTable accountTable){
        try {
            accountTable.save();
            return true;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }
}
