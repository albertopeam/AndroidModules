package apa.accessmodule.ui.presenter.login;

import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.accessmodule.ui.navigation.Page;
import apa.accessmodule.ui.presenter.PresenterAbs;

/**
 * Created by alberto on 3/1/16.
 */
public class LoginPresenterImpl extends PresenterAbs implements LoginPresenter, LoginUseCase.LoginCallback{


    private LoginUseCase loginUseCase;
    private LoginView view;
    private Page page;


    public LoginPresenterImpl(LoginUseCase loginUseCase, LoginView loginView, Page page) {
        this.loginUseCase = loginUseCase;
        this.view = loginView;
        this.page = page;
    }


    @Override
    public void create() {

    }

    @Override
    public void destroy() {
        loginUseCase = null;
        view = null;
    }

    @Override
    public void login(LoginForm loginForm){
        view.hideKeyboard();
        view.showLoading();
        loginUseCase.login(loginForm, this);
    }


    @Override
    public void logedIn(AccountBoundary accountBoundary) {
        view.clearErrors();
        view.hideLoading();
        view.logedIn();
        page.nextPage();
    }


    @Override
    public void loginError(String error) {
        view.clearErrors();
        view.hideLoading();
        view.loginError(error);
    }


    @Override
    public void invalidForm(List<FieldError> fieldErrors) {
        view.hideLoading();
        view.invalidForm(fieldErrors);
    }


}
