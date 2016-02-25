package apa.components.data.api.mapper;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.Mapper;
import apa.components.data.api.model.AccountCloud;

/**
 * Created by alberto on 7/2/16.
 */
public class AccountCloudMapper implements Mapper<AccountCloud, AccountEntity> {

    @Override
    public AccountEntity map(AccountCloud accountCloud) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(accountCloud.getEmail());
        accountEntity.setToken(accountCloud.getToken());
        return accountEntity;
    }

}
