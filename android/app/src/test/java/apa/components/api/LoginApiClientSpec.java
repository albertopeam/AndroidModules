package apa.components.api;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.formvalidator.form.LoginForm;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.client.LoginApiClient;
import apa.components.data.api.exception.LoginApiException;
import apa.components.data.api.factory.ApiRestFactory;
import apa.components.data.api.model.AccountCloud;
import apa.components.data.api.mapper.AccountCloudMapper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

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
    }


    private void setUpDefaults(){
        mapper = new AccountCloudMapper();
        loginApiRest = ApiRestFactory.createRetrofitService(LoginApiRest.class, getBaseEndpoint());
        loginApiClient = new LoginApiClient(loginApiRest, mapper);
    }


    @Test
    public void whenCredentialsAreOkThenReturnAccount() throws Exception{
        setUpDefaults();

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
        setUpDefaults();

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
        setUpDefaults();

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

    @Test(expected = LoginApiException.class)
    public void whenNetworkFailsThenThrowException()throws Exception{
        //TODO: SimpleMockService es un ejemplo
        //https://github.com/square/retrofit/blob/master/samples/src/main/java/com/example/retrofit/SimpleMockService.java

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseEndpoint())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setFailurePercent(100);
        behavior.setDelay(10, TimeUnit.MILLISECONDS);
        behavior.setVariancePercent(10);
        behavior.setFailureException(new IOException("Failure"));

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate<LoginApiRest> behaviorDelegate = mockRetrofit.create(LoginApiRest.class);

        LoginApiRest loginApiRest = new MockLoginApiRest(behaviorDelegate);


        loginApiClient = new LoginApiClient(loginApiRest, new AccountCloudMapper());
        try {
            loginApiClient.login(new LoginForm());
        }catch (LoginApiException e){
            assertThat(-1, equalTo(e.getCode()));
            assertThat("Failure", equalTo(e.getMessage()));
            throw e;
        }
    }


    static final class MockLoginApiRest implements LoginApiRest {
        private final BehaviorDelegate<LoginApiRest> delegate;
        public MockLoginApiRest(BehaviorDelegate<LoginApiRest> delegate) {
            this.delegate = delegate;
        }
        @Override
        public Call<AccountCloud> login(@Field("email") String email, @Field("password") String password) {
            AccountCloud accountCloud = new AccountCloud();
            return delegate.returningResponse(accountCloud).login(email, password);
        }
    }
}
