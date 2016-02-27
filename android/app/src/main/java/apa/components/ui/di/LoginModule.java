package apa.components.ui.di;


import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.model.mapper.AccountBoundaryToAccountEntityMapper;
import apa.accessmodule.data.repository.login.LoginDataRepository;
import apa.accessmodule.data.repository.login.LoginDataSource;
import apa.accessmodule.data.repository.login.sources.LoginCloudDataSource;
import apa.accessmodule.data.repository.store.StoreAccountSource;
import apa.accessmodule.data.repository.store.StoreDataAccountRepository;
import apa.accessmodule.domain.formvalidator.validator.LoginValidator;
import apa.accessmodule.domain.interactor.LoginInteractor;
import apa.accessmodule.domain.repository.LoginRepository;
import apa.accessmodule.domain.repository.StoreAccountRepository;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.accessmodule.ui.presenter.login.LoginPresenter;
import apa.accessmodule.ui.presenter.login.LoginPresenterImpl;
import apa.accessmodule.ui.view.AbsLoginActivity;
import apa.components.R;
import apa.components.data.cache.StoreAccountCacheDataSource;
import apa.components.data.database.mapper.AccountPersistenceMapper;
import apa.components.data.database.source.StoreAccountPersistenceDataSource;
import apa.components.global.di.module.ActivityModule;
import apa.components.global.di.scope.Cache;
import apa.components.global.di.scope.Database;
import apa.components.global.di.scope.ForActivity;
import apa.components.ui.navigation.LoginPage;
import apa.executor.Executor;
import apa.executor.MainThread;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 10/1/16.
 */
@Module
public class LoginModule extends ActivityModule{


    public LoginModule(AbsLoginActivity activity) {
        super(activity);
    }


    @ForActivity
    @Provides
    LoginPresenter providePresenter(LoginUseCase loginUseCase, LoginPage loginPage){
        return new LoginPresenterImpl(loginUseCase, (AbsLoginActivity)activity, loginPage);
    }


    @ForActivity
    @Provides
    LoginValidator provideValidator(){
        return new LoginValidator();
    }


    @ForActivity
    @Provides
    LoginUseCase provideUseCase(Executor executor, MainThread mainThread, LoginValidator loginValidator, LoginRepository loginRepository, StoreAccountRepository storeAccountRepository){
        return new LoginInteractor(executor, mainThread, loginValidator, loginRepository, storeAccountRepository, activity.getResources().getString(R.string.error));
    }


    @ForActivity
    @Provides
    StoreAccountRepository provideStoreAccountRepository(@Cache StoreAccountSource cacheStoreAccountSource, @Database StoreAccountSource dbStoreAccountSource){
        return new StoreDataAccountRepository(cacheStoreAccountSource, dbStoreAccountSource, new AccountBoundaryToAccountEntityMapper());
    }


    @ForActivity
    @Provides
    @Cache
    StoreAccountSource provideStoreAccountSourceCache(){
        return new StoreAccountCacheDataSource();
    }


    @ForActivity
    @Provides
    @Database
    StoreAccountSource provideStoreAccountSourceDB(){
        return new StoreAccountPersistenceDataSource(new AccountPersistenceMapper());
    }



    @ForActivity
    @Provides
    LoginRepository provideLoginRepository(LoginDataSource loginDataSource){
        return new LoginDataRepository(loginDataSource);
    }


    @ForActivity
    @Provides
    LoginDataSource provideLoginDataSource(LoginApi loginApi){
        return new LoginCloudDataSource(loginApi);
    }


    @ForActivity
    @Provides
    LoginPage provideLoginPage(){
        return new LoginPage(activity);
    }
}
