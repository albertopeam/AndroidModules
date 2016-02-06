package apa.accessmodule.data.repository;

import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.LoginRepository;

/**
 * Created by alberto on 30/1/16.
 */
public class LoginDataRepository implements LoginRepository {


    private LoginDataSource loginDataSource;


    public LoginDataRepository(LoginDataSource loginDataSource) {
        this.loginDataSource = loginDataSource;
    }


    @Override
    public AccountBoundary login(LoginForm loginForm){
        //AccountEntity accountEntity = loginDataSource.login(loginForm);
        return null;
    }
}
