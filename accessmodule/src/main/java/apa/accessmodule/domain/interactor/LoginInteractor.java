package apa.accessmodule.domain.interactor;

import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.formvalidator.validator.Validator;
import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.LoginRepository;
import apa.accessmodule.domain.usecase.UseCaseAbs;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.executor.Executor;
import apa.executor.MainThread;

/**
 * Created by alberto on 28/1/16.
 */
public class LoginInteractor extends UseCaseAbs implements LoginUseCase {


    private LoginRepository loginRepository;
    private LoginUseCase.LoginCallback callback;
    private LoginForm loginForm;
    private Validator loginValidator;


    public LoginInteractor(Executor executor, MainThread mainThread, Validator loginValidator, LoginRepository loginRepository) {
        super(executor, mainThread);
        this.loginValidator = loginValidator;
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(){
        List<FieldError>fieldErrors = loginValidator.validate();
        if (fieldErrors.isEmpty()){
            AccountBoundary accountBoundary = loginRepository.login(loginForm);
            onLogin(accountBoundary);
        }else{
            callback.invalidForm(fieldErrors);
        }
    }


    @Override
    public void login(LoginForm loginForm, LoginCallback loginCallback) {
        this.callback = loginCallback;
        this.loginForm = loginForm;
        runOnBackgroundThread(this);
    }


    private void onLogin(AccountBoundary accountBoundary){
        if (accountBoundary.isSuccess()){

        }else{
            onError(accountBoundary);
        }
    }


    private void onError(AccountBoundary accountBoundary){
        if (hasCallback()){
            final String error = accountBoundary.getException().getMessage();
            runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    callback.loginError(error);
                }
            });

        }
    }


    private boolean hasCallback(){
        return callback != null;
    }
}
