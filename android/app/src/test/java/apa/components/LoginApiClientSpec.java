package apa.components;

import org.junit.Before;
import org.junit.Test;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.client.LoginApiClient;
import apa.components.data.api.exception.LoginApiException;
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

        enqueueMockResponse("login_success.json");
        AccountEntity accountEntity = loginApiClient.login(loginForm);
        assertThat(accountEntity, equalTo(expectedAccountEntity));
    }

    @Test(expected = LoginApiException.class)
    public void whenCredentialsAreWrongThenThrowException() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("a@gmail.com");
        loginForm.setPassword("");

        enqueueMockResponse(401, getContentFromFile("login_error_bad_credentials.json"));
        try {
            loginApiClient.login(loginForm);
        }catch (LoginApiException e){
            assertThat(401, equalTo(e.getCode()));
            assertThat("Email or password are wrong", equalTo(e.getMessage()));
            throw e;
        }
    }


    @Test(expected = LoginApiException.class)
    public void whenAccountNotExistsThenThrowException() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("jacinto@gmail.com");
        loginForm.setPassword("qwerty");

        enqueueMockResponse(404, getContentFromFile("login_error_not_found.json"));
        try {
            loginApiClient.login(loginForm);
        }catch (LoginApiException e){
            assertThat(404, equalTo(e.getCode()));
            assertThat("User not found", equalTo(e.getMessage()));
            throw e;
        }
    }
}
