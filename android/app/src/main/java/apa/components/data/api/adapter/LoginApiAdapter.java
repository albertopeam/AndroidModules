package apa.components.data.api.adapter;

import java.io.IOException;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.LoginAccountApi;
import apa.components.data.api.cloud.AccountCloud;
import apa.components.data.api.cloud.AccountCloudMapper;
import apa.components.data.api.exception.LoginErrorException;
import retrofit2.Call;

/**
 * Created by alberto on 7/2/16.
 */
public class LoginApiAdapter implements apa.accessmodule.data.api.LoginApi {


    private LoginAccountApi loginAccountApi;
    private AccountCloudMapper mapper;


    public LoginApiAdapter(LoginAccountApi loginAccountApi, AccountCloudMapper accountCloudMapper) {
        this.loginAccountApi = loginAccountApi;
        this.mapper = accountCloudMapper;
    }


    @Override
    public AccountEntity login(LoginForm loginForm) throws Exception{
        Call<AccountCloud> call = loginAccountApi.login(loginForm);
        AccountCloud accountCloud = tryCall(call);
        AccountEntity accountEntity = mapper.map(accountCloud);
        return accountEntity;
    }


    private AccountCloud tryCall(Call<AccountCloud>call) throws LoginErrorException{
        try {
            AccountCloud accountCloud = call.execute().body();
            return accountCloud;
        }catch (IOException e) {
            throw new LoginErrorException(getMessage(e));
        }
    }

    private String getMessage(Exception e){
        String message = e.getMessage() != null?e.getMessage():"";
        return message;
    }
}
