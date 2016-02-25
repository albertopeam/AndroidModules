package apa.accessmodule.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.login.sources.LoginCloudDataSource;
import apa.accessmodule.domain.formvalidator.form.LoginForm;

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
    public void whenApiReturnsAccountThenReturnAccount() throws Exception{
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail("a@gmail.com");
        accountEntity.setToken("abcd1234");

        when(loginApiMock.login(loginFormMock)).thenAnswer(new Answer<AccountEntity>() {
            @Override
            public AccountEntity answer(InvocationOnMock invocation) throws Throwable {
                final AccountEntity accountEntity1 = new AccountEntity();
                accountEntity1.setEmail("a@gmail.com");
                accountEntity1.setToken("abcd1234");
                return accountEntity1;
            }
        });

        AccountEntity returnedAccountEntity = loginCloudDataSource.login(loginFormMock);
        assertThat(returnedAccountEntity, equalTo(accountEntity));

    }

}
