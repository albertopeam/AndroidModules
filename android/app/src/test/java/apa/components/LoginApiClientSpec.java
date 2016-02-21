package apa.components;

import org.junit.Before;
import org.junit.Test;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.client.LoginApiClient;
import apa.components.data.api.factory.ApiRestFactory;
import apa.components.data.api.model.AccountCloudMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by alberto on 20/2/16.
 */
public class LoginApiClientSpec extends ApiClientTest{


    private LoginApiRest loginApiRest;
    private AccountCloudMapper mapper;
    private LoginApiClient loginApiClient;


    @Before
    public void setUp() throws Exception{
        super.setUp();

        mapper = new AccountCloudMapper();
        loginApiRest = ApiRestFactory.createRetrofitService(LoginApiRest.class, getBaseEndpoint());
        loginApiClient = new LoginApiClient(loginApiRest, mapper);
    }


    @Test
    public void whenCredentialsAreOkThenReturnAccount() throws Exception{
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("a@gmail.com");
        loginForm.setPassword("qwertyuiop");

        AccountEntity expectedAccountEntity = new AccountEntity();
        expectedAccountEntity.setEmail("a@gmail.com");
        expectedAccountEntity.setToken("JuqiGPjuvzkn_23Jt2-x");

        enqueueMockResponse("success_login.json");
        AccountEntity accountEntity = loginApiClient.login(loginForm);
        assertThat(accountEntity, equalTo(expectedAccountEntity));
    }
    
}
