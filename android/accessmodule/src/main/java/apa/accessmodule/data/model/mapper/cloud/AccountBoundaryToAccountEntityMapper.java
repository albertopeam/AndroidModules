package apa.accessmodule.data.model.mapper.cloud;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.Mapper;
import apa.accessmodule.domain.repository.AccountBoundary;

/**
 * Created by alberto on 7/2/16.
 */
public class AccountBoundaryToAccountEntityMapper implements Mapper<AccountBoundary, AccountEntity> {

    @Override
    public AccountEntity map(AccountBoundary accountBoundary) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(accountBoundary.getEmail());
        accountEntity.setToken(accountBoundary.getToken());
        return accountEntity;
    }

}
