package apa.accessmodule.data.repository.login.sources;


import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.repository.login.LoginDataSource;
import apa.accessmodule.domain.formvalidator.form.LoginForm;

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
        AccountEntity accountEntity = api.login(loginForm);
        return accountEntity;
    }
}
