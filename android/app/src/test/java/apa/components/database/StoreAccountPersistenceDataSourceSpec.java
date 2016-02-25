package apa.components.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import apa.accessmodule.data.model.entity.AccountEntity;
import apa.components.data.database.AccountTable;
import apa.components.data.database.mapper.AccountPersistenceMapper;
import apa.components.data.database.source.StoreAccountPersistenceDataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 25/2/16.
 */
public class StoreAccountPersistenceDataSourceSpec {

    @Mock AccountPersistenceMapper mapperMock;
    @Mock AccountTable accountTableMock;
    private AccountEntity accountEntity;
    private StoreAccountPersistenceDataSource dataSource;
    private String ANY_EMAIL = "a@gmail.com";
    private String ANY_TOKEN = "asdfghjklñ";
    private long ANY_ID = 1;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(accountTableMock.getEmail()).thenReturn(ANY_EMAIL);
        when(accountTableMock.getToken()).thenReturn(ANY_TOKEN);
        when(mapperMock.map(any(AccountEntity.class))).thenReturn(accountTableMock);

        accountEntity = new AccountEntity();
        accountEntity.setEmail(ANY_EMAIL);
        accountEntity.setToken(ANY_TOKEN);
        dataSource = new StoreAccountPersistenceDataSource(mapperMock);
    }


    @Test
    public void whenDatabaseSaveThenReturnTrue(){
        boolean stored = dataSource.store(accountEntity);
        verify(accountTableMock, times(1)).save();
        assertThat(stored, is(true));
    }


    @Test
    public void whenDatabaseFailsSaveThenReturnFalse(){
        doThrow(new IllegalArgumentException("")).when(accountTableMock).save();

        boolean stored = dataSource.store(accountEntity);
        verify(accountTableMock, times(1)).save();
        assertThat(stored, is(false));
    }
}
