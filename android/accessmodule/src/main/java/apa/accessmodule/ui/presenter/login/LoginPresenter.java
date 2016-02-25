package apa.accessmodule.ui.presenter.login;

import apa.accessmodule.domain.formvalidator.form.LoginForm;
import apa.accessmodule.ui.presenter.Presenter;

/**
 * Created by alberto on 9/2/16.
 */
public interface LoginPresenter extends Presenter {
    void login(LoginForm loginForm);

}
