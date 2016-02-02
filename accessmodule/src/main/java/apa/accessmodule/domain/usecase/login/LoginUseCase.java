package apa.accessmodule.domain.usecase.login;

import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.model.LoginForm;

public interface LoginUseCase {
    void login(LoginForm loginForm, LoginCallback loginCallback);

    interface LoginCallback {
        void logedIn();
        void loginError(String error);
        void invalidForm(List<FieldError> fieldErrors);
    }
}