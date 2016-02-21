package apa.components.global.di.module.net;

import javax.inject.Singleton;

import apa.accessmodule.data.api.LoginApi;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.client.LoginApiClient;
import apa.components.data.api.model.AccountCloudMapper;
import apa.components.data.api.factory.ApiRestFactory;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 20/2/16.
 */
@Module
public class NetworkModule {


    private String ENDPOINT = "http://localhost:3000/api";


    @Provides
    @Singleton
    LoginApi provideLoginApi(AccountCloudMapper accountCloudMapper){
        LoginApiRest api = ApiRestFactory.createRetrofitService(LoginApiRest.class, ENDPOINT);
        return new LoginApiClient(api, accountCloudMapper);
    }

    @Provides
    @Singleton
    AccountCloudMapper provideMapper(){
        return new AccountCloudMapper();
    }
}
