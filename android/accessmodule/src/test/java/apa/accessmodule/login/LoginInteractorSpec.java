package apa.accessmodule.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import apa.accessmodule.domain.formvalidator.model.FieldError;
import apa.accessmodule.domain.formvalidator.validator.LoginValidator;
import apa.accessmodule.domain.interactor.LoginInteractor;
import apa.accessmodule.domain.formvalidator.form.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.repository.LoginRepository;
import apa.accessmodule.domain.repository.StoreAccountRepository;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.executor.Executor;
import apa.executor.Interactor;
import apa.executor.MainThread;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 28/1/16.
 */
public class LoginInteractorSpec {


    @Mock MainThread mainThreadMock;
    @Mock Executor executorMock;
    @Mock LoginUseCase.LoginCallback callbackMock;
    @Mock LoginRepository loginRepositoryMock;
    @Mock LoginInteractor loginInteractor;
    @Mock LoginValidator loginValidator;
    @Mock LoginForm loginFormMock;
    @Mock StoreAccountRepository storeAccountRepositoryMock;
    @Captor ArgumentCaptor<List<FieldError>> listArgumentCaptor;
    @Captor ArgumentCaptor<String > errorArgumentCaptor;
    @Captor ArgumentCaptor<AccountBoundary> accountBoundaryArgumentCaptor;
    String errorStore = "Error storing account";


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        loginInteractor = new LoginInteractor(executorMock, mainThreadMock, loginValidator,loginRepositoryMock, storeAccountRepositoryMock, errorStore);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Runnable)invocation.getArguments()[0]).run();
                return new AccountBoundary();
            }
        }).when(executorMock).runInBackgroundThread(any(Interactor.class));

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Runnable)invocation.getArguments()[0]).run();
                return null;
            }
        }).when(mainThreadMock).runInMainThread(any(Runnable.class));
    }


    @Test
    public void whenFormIsNotValidThenInvokeInvalidFormCallback(){
        when(loginValidator.validate()).thenAnswer(new Answer<List<FieldError>>() {
            @Override
            public List<FieldError> answer(InvocationOnMock invocation) throws Throwable {
                List<FieldError>fieldErrors = new ArrayList<FieldError>();
                FieldError fieldError = new FieldError();
                fieldErrors.add(fieldError);
                return fieldErrors;
            }
        });
        loginInteractor.login(new LoginForm(), callbackMock);


        verify(executorMock).runInBackgroundThread(any(LoginInteractor.class));
        verify(callbackMock).invalidForm(listArgumentCaptor.capture());
        assertThat(listArgumentCaptor.getValue().size(), is(1));

        verifyNoMoreInteractions(executorMock);
        verifyNoMoreInteractions(loginRepositoryMock);
    }


    @Test
    public void whenAccountNotExistsThenError(){
        LoginForm loginForm = new LoginForm();

        when(loginValidator.validate()).thenAnswer(new Answer<List<FieldError>>() {
            @Override
            public List<FieldError> answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<>();
            }
        });
        when(loginRepositoryMock.login(loginForm)).thenAnswer(new Answer<AccountBoundary>() {
            @Override
            public AccountBoundary answer(InvocationOnMock invocationOnMock) throws Throwable {
                AccountBoundary accountBoundary = new AccountBoundary();
                Exception exception = new Exception("User not exists");
                accountBoundary.setException(exception);
                return accountBoundary;
            }
        });
        loginInteractor.login(loginForm, callbackMock);

        verify(executorMock, times(1)).runInBackgroundThread(any(LoginInteractor.class));
        verify(callbackMock, times(1)).loginError(errorArgumentCaptor.capture());
        verify(mainThreadMock).runInMainThread(any(Runnable.class));
        assertThat(errorArgumentCaptor.getValue(), not(isEmptyString()));

        verifyNoMoreInteractions(executorMock);
        verifyNoMoreInteractions(callbackMock);
    }


    @Test
    public void whenApiReturnAccountThenThenSuccessStoring(){
        LoginForm loginForm = new LoginForm();
        AccountBoundary accountBoundary = new AccountBoundary();
        accountBoundary.setEmail("a@gmail.com");
        accountBoundary.setToken("abcd1234");

        when(loginValidator.validate()).thenAnswer(new Answer<List<FieldError>>() {
            @Override
            public List<FieldError> answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<>();
            }
        });
        when(loginRepositoryMock.login(loginForm)).thenAnswer(new Answer<AccountBoundary>() {
            @Override
            public AccountBoundary answer(InvocationOnMock invocationOnMock) throws Throwable {
                AccountBoundary mockAccountBoundary = new AccountBoundary();
                mockAccountBoundary.setEmail("a@gmail.com");
                mockAccountBoundary.setToken("abcd1234");
                return mockAccountBoundary;
            }
        });
        when(storeAccountRepositoryMock.store(accountBoundary)).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return true;
            }
        });
        loginInteractor.login(loginForm, callbackMock);

        verify(executorMock, times(1)).runInBackgroundThread(any(LoginInteractor.class));
        verify(storeAccountRepositoryMock, times(1)).store(any(AccountBoundary.class));
        verify(mainThreadMock).runInMainThread(any(Runnable.class));
        verify(callbackMock, times(1)).logedIn(accountBoundaryArgumentCaptor.capture());
        assertThat(accountBoundaryArgumentCaptor.getValue(), equalTo(accountBoundary));

        verifyNoMoreInteractions(executorMock);
        verifyNoMoreInteractions(callbackMock);
    }


    @Test
    public void whenApiReturnAccountThenFailStoring(){
        final AccountBoundary accountBoundary = new AccountBoundary();
        accountBoundary.setEmail("a@gmail.com");
        accountBoundary.setToken("abcd1234");

        when(loginValidator.validate()).thenAnswer(new Answer<List<FieldError>>() {
            @Override
            public List<FieldError> answer(InvocationOnMock invocation) throws Throwable {
                return new ArrayList<>();
            }
        });

        when(loginRepositoryMock.login(loginFormMock)).thenAnswer(new Answer<AccountBoundary>() {
            @Override
            public AccountBoundary answer(InvocationOnMock invocationOnMock) throws Throwable {
                return accountBoundary;
            }
        });

        when(storeAccountRepositoryMock.store(accountBoundary)).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return false;
            }
        });

        loginInteractor.login(loginFormMock, callbackMock);

        verify(executorMock, times(1)).runInBackgroundThread(any(LoginInteractor.class));
        verify(storeAccountRepositoryMock, times(1)).store(any(AccountBoundary.class));
        verify(mainThreadMock).runInMainThread(any(Runnable.class));
        verify(callbackMock, times(1)).loginError(errorArgumentCaptor.capture());
        assertThat(errorArgumentCaptor.getValue(), equalTo(errorStore));
        verifyNoMoreInteractions(executorMock);
        verifyNoMoreInteractions(callbackMock);
    }
}
