package apa.accessmodule.ui;

import apa.accessmodule.domain.formvalidator.validator.Validator;
import apa.accessmodule.domain.usecase.login.LoginUseCase;

/**
 * Created by alberto on 3/1/16.
 */
public class LoginPresenter {


    private Validator loginValidator;
    private LoginUseCase loginUseCase;


    public LoginPresenter(Validator validator, LoginUseCase loginUseCase) {
        this.loginValidator = validator;
        this.loginUseCase = loginUseCase;
    }
}
