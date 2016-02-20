package apa.components.global.di.module.net;

import javax.inject.Singleton;

import apa.accessmodule.data.api.LoginApi;
import apa.components.data.api.LoginAccountApi;
import apa.components.data.api.adapter.LoginApiAdapter;
import apa.components.data.api.cloud.AccountCloudMapper;
import apa.components.data.api.factory.ApiFactory;
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
        LoginAccountApi api = ApiFactory.createRetrofitService(LoginAccountApi.class, ENDPOINT);
        return new LoginApiAdapter(api, accountCloudMapper);
    }

    @Provides
    @Singleton
    AccountCloudMapper provideMapper(){
        return new AccountCloudMapper();
    }
}
