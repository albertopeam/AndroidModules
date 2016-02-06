package apa.accessmodule.domain.interactor;

import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.formvalidator.validator.Validator;
import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.LoginRepository;
import apa.accessmodule.domain.repository.StoreAccountRepository;
import apa.accessmodule.domain.usecase.UseCaseAbs;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.executor.Executor;
import apa.executor.MainThread;

/**
 * Created by alberto on 28/1/16.
 */
public class LoginInteractor extends UseCaseAbs implements LoginUseCase {


    private LoginRepository loginRepository;
    private StoreAccountRepository storeAccountRepository;
    private LoginUseCase.LoginCallback callback;
    private LoginForm loginForm;
    private Validator loginValidator;
    private String errorStore;


    public LoginInteractor(Executor executor, MainThread mainThread, Validator loginValidator, LoginRepository loginRepository, StoreAccountRepository storeAccountRepository, String errorStore) {
        super(executor, mainThread);
        this.loginValidator = loginValidator;
        this.loginRepository = loginRepository;
        this.storeAccountRepository = storeAccountRepository;
        this.errorStore = errorStore;
    }


    @Override
    public void login(LoginForm loginForm, LoginCallback loginCallback) {
        this.callback = loginCallback;
        this.loginForm = loginForm;
        runOnBackgroundThread(this);
    }


    @Override
    public void run(){
        login();
    }


    private void login(){
        List<FieldError>fieldErrors = loginValidator.validate();
        if (fieldErrors.isEmpty()){
            AccountBoundary accountBoundary = loginRepository.login(loginForm);
            onEndLogin(accountBoundary);
        }else{
            callback.invalidForm(fieldErrors);
        }
    }


    private void onEndLogin(AccountBoundary accountBoundary){
        if (accountBoundary.isSuccess()){
            store(accountBoundary);
        }else{
            onError(accountBoundary);
        }
    }


    private void store(AccountBoundary accountBoundary){
        if (storeAccountRepository.store(accountBoundary)){
            onSuccess(accountBoundary);
        }else{
            accountBoundary.setException(new Exception(errorStore));
            onError(accountBoundary);
        }
    }


    private void onSuccess(AccountBoundary accountBoundary){
        if (hasCallback()){
            final AccountBoundary returnAccountBoundary = accountBoundary;
            runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    callback.logedIn(returnAccountBoundary);
                }
            });
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