package apa.accessmodule;

import org.junit.Test;

import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.domain.repository.AccountBoundary;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by alberto on 31/1/16.
 */
public class AccountBoundarySpec {


    @Test
    public void whenAccountSuppliedToAccountBoundaryThenAreEquals(){
        AccountCloud accountCloud = new AccountCloud();
        accountCloud.setEmail("a@gmail.com");
        accountCloud.setToken("askdalsdknaskdansldnasdlknasdlkansd");
        AccountBoundary boundary = new AccountBoundary();
        assertThat(accountCloud.getEmail(), equals(boundary.getEmail()));
        assertThat(accountCloud.getToken(), equals(boundary.getToken()));
    }
}
