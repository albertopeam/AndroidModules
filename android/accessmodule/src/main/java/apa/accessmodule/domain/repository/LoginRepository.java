package apa.accessmodule.domain.repository;

import apa.accessmodule.domain.formvalidator.form.LoginForm;

/**
 * Created by alberto on 29/1/16.
 */
public interface LoginRepository {
    AccountBoundary login(LoginForm loginForm);
}
