package apa.accessmodule.data.api;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.formvalidator.form.LoginForm;

/**
 * Created by alberto on 7/2/16.
 */
public interface LoginApi {
    AccountEntity login(LoginForm loginForm) throws Exception;
}
