package apa.components.data.database.mapper;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.Mapper;
import apa.components.data.database.AccountTable;

/**
 * Created by alberto on 25/2/16.
 */
public class AccountPersistenceMapper implements Mapper<AccountEntity, AccountTable>{


    @Override
    public AccountTable map(AccountEntity accountEntity) {
        AccountTable accountTable = new AccountTable();
        accountTable.setEmail(accountEntity.getEmail());
        accountTable.setToken(accountEntity.getToken());
        return accountTable;
    }
}
