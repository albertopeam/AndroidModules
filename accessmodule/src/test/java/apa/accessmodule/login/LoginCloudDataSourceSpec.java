package apa.accessmodule.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.model.cloud.AccountCloud;
import apa.accessmodule.data.repository.login.sources.LoginCloudDataSource;
import apa.accessmodule.domain.model.LoginForm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


/**
 * Created by alberto on 6/2/16.
 */
public class LoginCloudDataSourceSpec {

    
    @Mock LoginApi loginApiMock;
    @Mock LoginForm loginFormMock;
    LoginCloudDataSource loginCloudDataSource;
    //TODO: meter mockServer square.


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginCloudDataSource = new LoginCloudDataSource(loginApiMock);
    }
    
    
    @Test(expected = Exception.class)
    public void whenApiThrowsExceptionThenReturnException() throws Exception{
        when(loginApiMock.login(loginFormMock)).thenThrow(new Exception("No user account"));

        loginCloudDataSource.login(loginFormMock);
        verifyNoMoreInteractions(loginCloudDataSource);
        verifyNoMoreInteractions(loginApiMock);
    }
    

    @Test
    public void whenApiReturnsAccountThenReturnAccoun() throws Exception{
        AccountCloud accountCloud = new AccountCloud();
        accountCloud.setEmail("a@gmail.com");
        accountCloud.setToken("abcd1234");

        when(loginApiMock.login(loginFormMock)).thenAnswer(new Answer<AccountCloud>() {
            @Override
            public AccountCloud answer(InvocationOnMock invocation) throws Throwable {
                AccountCloud accountCloudAnswer = new AccountCloud();
                accountCloudAnswer.setEmail("a@gmail.com");
                accountCloudAnswer.setToken("abcd1234");
                return accountCloudAnswer;
            }
        });

        AccountCloud accountCloudResponse = loginCloudDataSource.login(loginFormMock);
        assertThat(accountCloudResponse, equalTo(accountCloud));

    }


}
