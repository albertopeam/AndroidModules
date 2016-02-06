package apa.accessmodule.login;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import apa.accessmodule.data.repository.LoginDataRepository;
import apa.accessmodule.data.repository.datasource.LoginCloudDataSource;
import apa.accessmodule.domain.model.LoginForm;

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


    test con exception
    test con accountEntity/accountBoundary
}
