package apa.components;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import apa.components.data.api.LoginAccountApi;
import apa.components.data.api.adapter.LoginApiAdapter;
import apa.components.data.api.cloud.AccountCloudMapper;

/**
 * Created by alberto on 20/2/16.
 */
public class LoginApiAdapterSpec {

    @Mock LoginAccountApi apiMock;
    @Mock AccountCloudMapper mapperMock;
    private LoginApiAdapter adapter;

    @Before
    public void setUp(){
        adapter = new LoginApiAdapter(apiMock, mapperMock);
    }

    @Test
    public void whenCredentialsAreOkThenReturnAccount(){
        todo
    }
}
