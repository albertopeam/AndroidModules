package apa.accessmodule.data.repository.login;

import apa.accessmodule.data.model.entity.AccountEntity;
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
        AccountBoundary accountBoundary = new AccountBoundary();
        try {
            AccountEntity accountEntity = loginDataSource.login(loginForm);
            accountBoundary.setToken(accountEntity.getToken());
            accountBoundary.setEmail(accountEntity.getEmail());
        } catch (Exception e) {
            accountBoundary.setException(e);
        }
        return accountBoundary;
    }
}
