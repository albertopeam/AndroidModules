package apa.components;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.LoginAccountApi;
import apa.components.data.api.cloud.AccountCloud;
import apa.components.data.api.factory.ApiFactory;
import retrofit2.Call;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 20/2/16.
 */
public class LoginApiSpec extends ApiClientTest {


    private String ANY_ENDPOINT = "http://fake/api";
    private LoginAccountApi api;
    @Mock LoginForm loginFormMock;


    @Before
    public void setUp(){
        api = ApiFactory.createRetrofitService(LoginAccountApi.class, ANY_ENDPOINT);
    }


    @Test
    public void whenCredentialsAreOkThenReturnAccount() throws Exception{
        enqueueMockResponse("success_login.json");

        when(loginFormMock).;
        Call<AccountCloud> call = api.login(loginFormMock);
        AccountCloud accountCloud = call.execute().body();
        assertThat(accountCloud, );
    }
}
