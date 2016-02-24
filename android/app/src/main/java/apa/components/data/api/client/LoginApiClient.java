package apa.components.data.api.client;

import com.google.gson.Gson;

import java.io.IOException;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.domain.model.LoginForm;
import apa.components.data.api.LoginApiRest;
import apa.components.data.api.exception.LoginApiException;
import apa.components.data.api.model.AccountCloud;
import apa.components.data.api.model.AccountCloudMapper;
import apa.components.data.api.model.ApiError;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by alberto on 7/2/16.
 */
public class LoginApiClient implements apa.accessmodule.data.api.LoginApi {


    private LoginApiRest loginApiRest;
    private AccountCloudMapper mapper;


    public LoginApiClient(LoginApiRest loginApiRest, AccountCloudMapper accountCloudMapper) {
        this.loginApiRest = loginApiRest;
        this.mapper = accountCloudMapper;
    }


    @Override
    public AccountEntity login(LoginForm loginForm) throws Exception{
        Call<AccountCloud> call = loginApiRest.login(loginForm.getEmail(), loginForm.getPassword());
        AccountCloud accountCloud = tryCall(call);
        AccountEntity accountEntity = mapper.map(accountCloud);
        return accountEntity;
    }


    private AccountCloud tryCall(Call<AccountCloud>call) throws LoginApiException {
        try {
            Response<AccountCloud>response = call.execute();
            if (response.isSuccess()){
                AccountCloud accountCloud = response.body();
                return accountCloud;
            }else{
                if (hasErrorBody(response)) {
                    try {
                        ApiError apiError = new Gson().fromJson(response.errorBody().string(), ApiError.class);
                        throw new LoginApiException(code(response), apiError.message);
                    } catch (IOException e) {
                        throw new LoginApiException(code(response), e.getMessage());
                    }
                }else{
                    throw new LoginApiException(code(response));
                }
            }
        }catch (IOException e) {
            throw new LoginApiException(getMessage(e));
        }
    }

    private int code(Response response){
        return response.code();
    }

    private boolean hasErrorBody(Response response){
        return response.errorBody() != null;
    }

    private String getMessage(Exception e){
        String message = e.getMessage() != null?e.getMessage():"";
        return message;
    }

}
