package apa.accessmodule.ui.presenter.login;

import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;

/**
 * Created by alberto on 9/2/16.
 */
public interface LoginView {
    void showLoading();
    void hideLoading();
    void hideKeyboard();
    void logedIn();
    void loginError(String error);
    void invalidForm(List<FieldError> fieldErrors);
    public void clearErrors();
}
