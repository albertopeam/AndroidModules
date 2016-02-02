package apa.accessmodule;

import org.junit.Test;

import apa.accessmodule.data.entity.AccountEntity;
import apa.accessmodule.domain.repository.AccountBoundary;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alberto on 31/1/16.
 */
public class AccountBoundarySpec {


    @Test
    public void whenAccountSuppliedToAccountBoundaryThenAreEquals(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail("a@gmail.com");
        accountEntity.setToken("askdalsdknaskdansldnasdlknasdlkansd");
        AccountBoundary boundary = new AccountBoundary();
        assertThat(accountEntity.getEmail(), equals(boundary.getEmail()));
        assertThat(accountEntity.getToken(), equals(boundary.getToken()));
    }
}
