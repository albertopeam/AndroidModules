package apa.accessmodule.data.repository.datasource;

import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.entity.AccountEntity;
import apa.accessmodule.data.repository.LoginDataSource;
import apa.accessmodule.domain.model.LoginForm;

/**
 * Created by alberto on 30/1/16.
 */
public class LoginCloudDataSource implements LoginDataSource {


    private LoginApi api;


    public LoginCloudDataSource(LoginApi api) {
        this.api = api;
    }


    @Override
    public AccountEntity login(LoginForm loginForm) throws Exception{
        return api.login(loginForm);
    }
}
