package apa.components.global.di.module.net;

import apa.accessmodule.data.api.LoginApi;
import apa.components.BuildConfig;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.client.LoginApiClient;
import apa.components.data.api.factory.ApiRestFactory;
import apa.components.data.api.mapper.AccountCloudMapper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by alberto on 20/2/16.
 */
@Module
public class NetworkModule {


    private String LOCAL_ENDPOINT = "http://192.168.0.12:3000/api/";
    protected String ENDPOINT = BuildConfig.DEBUG?LOCAL_ENDPOINT:LOCAL_ENDPOINT;


    @Provides
    LoginApi provideLoginApi(AccountCloudMapper accountCloudMapper){
        LoginApiRest api = ApiRestFactory.createRetrofitService(LoginApiRest.class, ENDPOINT);
        return new LoginApiClient(api, accountCloudMapper);
    }


    @Provides
    AccountCloudMapper provideMapper(){
        return new AccountCloudMapper();
    }
}
