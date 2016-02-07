package apa.accessmodule.data.repository.login.sources;


import apa.accessmodule.data.api.LoginApi;
import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.cloud.AccountCloudMapper;
import apa.accessmodule.data.repository.login.LoginDataSource;
import apa.accessmodule.domain.model.LoginForm;

/**
 * Created by alberto on 30/1/16.
 */
public class LoginCloudDataSource implements LoginDataSource {


    private LoginApi api;
    private AccountCloudMapper mapper;


    public LoginCloudDataSource(LoginApi api, AccountCloudMapper accountCloudMapper) {
        this.api = api;
        this.mapper = accountCloudMapper;
    }


    @Override
    public AccountEntity login(LoginForm loginForm) throws Exception{
        //todo
        //TODO: conex a api... exception pa arriba si peta o lo que sea
        return null;
    }
}
