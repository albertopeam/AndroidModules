package apa.accessmodule.store;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.accessmodule.data.model.mapper.cloud.AccountBoundaryToAccountEntityMapper;
import apa.accessmodule.data.repository.store.StoreAccountSource;
import apa.accessmodule.data.repository.store.StoreDataAccountRepository;
import apa.accessmodule.domain.repository.AccountBoundary;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 7/2/16.
 */
public class StoreDataAccountRepositorySpec {


    @Mock StoreAccountSource persistanceSourceMock;
    @Mock StoreAccountSource cacheSourceMock;
    @Mock AccountEntity accountEntityMock;
    @Mock AccountBoundary accountBoundaryMock;
    @Mock AccountBoundaryToAccountEntityMapper mapperMock;
    private StoreDataAccountRepository repository;



    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        repository = new StoreDataAccountRepository(cacheSourceMock, persistanceSourceMock, mapperMock);

        when(accountBoundaryMock.getEmail()).thenReturn("a@gmail.com");
        when(accountBoundaryMock.getToken()).thenReturn("abcd1234");
        when(accountEntityMock.getEmail()).thenReturn("a@gmail.com");
        when(accountEntityMock.getToken()).thenReturn("abcd1234");
    }

    @Test
    public void whenDataSourcesSaveThenReturnTrue(){
        when(mapperMock.map(accountBoundaryMock)).thenAnswer(new Answer<AccountEntity>() {
            @Override
            public AccountEntity answer(InvocationOnMock invocation) throws Throwable {
                return accountEntityMock;
            }
        });
        when(cacheSourceMock.store(accountEntityMock)).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return true;
            }
        });
        when(persistanceSourceMock.store(accountEntityMock)).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return true;
            }
        });
        boolean result = repository.store(accountBoundaryMock);
        assertThat(true, equalTo(result));
    }
}
