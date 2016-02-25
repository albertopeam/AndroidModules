package apa.accessmodule.data.repository.login;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.formvalidator.form.LoginForm;

/**
 * Created by alberto on 30/1/16.
 */
public interface LoginDataSource {
    AccountEntity login(LoginForm loginForm) throws Exception;
}
