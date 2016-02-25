package apa.components.api;

import org.junit.Before;
import org.junit.Test;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.components.data.api.mapper.AccountCloudMapper;
import apa.components.data.api.model.AccountCloud;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by alberto on 7/2/16.
 */
public class AccountCloudMapperSpec {

    private AccountCloudMapper mapper;
    private AccountCloud accountCloud;

    @Before
    public void setUp(){
        mapper = new AccountCloudMapper();
        accountCloud = new AccountCloud();
        accountCloud.setToken("asdjnaskdjnas");
        accountCloud.setEmail("a@gmail.com");
    }


    @Test
    public void whenMapThenAccountEntityHasSameProperties(){
        AccountEntity accountEntity = mapper.map(accountCloud);
        assertThat(accountCloud.getEmail(), equalTo(accountEntity.getEmail()));
        assertThat(accountCloud.getToken(), equalTo(accountEntity.getToken()));
    }


}
