package apa.accessmodule.data.repository;

import apa.accessmodule.data.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginDataSource {
    AccountEntity login(LoginForm loginForm) throws Exception;
}
