package apa.accessmodule.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.login.LoginDataRepository;
import apa.accessmodule.data.repository.login.sources.LoginCloudDataSource;
import apa.accessmodule.domain.formvalidator.form.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 6/2/16.
 */
public class LoginDataRepositorySpec {


    @Mock LoginCloudDataSource loginCloudDataSourceMock;
    @Mock LoginForm loginFormMock;
    LoginDataRepository loginDataRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginDataRepository = new LoginDataRepository(loginCloudDataSourceMock);
    }


    @Test
    public void whenDataSourceThrowExceptionThenBoundaryHasException() throws Exception{
        when(loginCloudDataSourceMock.login(loginFormMock)).thenThrow(new Exception("User has no account"));

        AccountBoundary accountBoundary = loginDataRepository.login(loginFormMock);
        assertThat(accountBoundary.getException(), notNullValue(Exception.class));
    }

    @Test
    public void whenDataSourceLoginCorrectyThenBoundaryHasData() throws Exception{
        AccountBoundary accountBoundary = new AccountBoundary();
        accountBoundary.setEmail("a@gmail.com");
        accountBoundary.setToken("asjbdakjsdnakjd");

        when(loginCloudDataSourceMock.login(loginFormMock)).thenAnswer(new Answer<AccountEntity>() {
            @Override
            public AccountEntity answer(InvocationOnMock invocation) throws Throwable {
                AccountEntity accountEntityMocked = new AccountEntity();
                accountEntityMocked.setEmail("a@gmail.com");
                accountEntityMocked.setToken("asjbdakjsdnakjd");
                return accountEntityMocked;
            }
        });

        AccountBoundary resultAccountBoundary = loginDataRepository.login(loginFormMock);
        assertThat(resultAccountBoundary, equalTo(accountBoundary));
    }
}
