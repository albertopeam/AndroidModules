package apa.accessmodule.presenter;

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
import apa.accessmodule.domain.model.LoginForm;
import apa.accessmodule.domain.repository.AccountBoundary;
import apa.accessmodule.domain.usecase.login.LoginUseCase;
import apa.accessmodule.ui.navigation.Page;
import apa.accessmodule.ui.presenter.login.LoginPresenterImpl;
import apa.accessmodule.ui.presenter.login.LoginView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by alberto on 10/2/16.
 */
public class LoginPresenterSpec {


    @Mock LoginUseCase loginUseCaseMock;
    @Mock LoginView viewMock;
    @Mock LoginForm loginFormMock;
    @Mock LoginUseCase.LoginCallback callbackMock;
    @Mock FieldError fieldErrorMock;
    @Mock Page<Void> pageMock;
    @Captor ArgumentCaptor<List<FieldError>> errorListArgumentCaptor;
    @Captor ArgumentCaptor<String> errorArgumentCaptor;
    private LoginPresenterImpl presenter;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenterImpl(loginUseCaseMock, viewMock, pageMock);
        when(fieldErrorMock.getReference()).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        });
        when(fieldErrorMock.getError()).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 0;
            }
        });
    }


    @Test
    public void whenInvalidFormThenShowError(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                LoginUseCase.LoginCallback loginCallback = (LoginUseCase.LoginCallback)invocation.getArguments()[1];
                List<FieldError>fieldErrors = new ArrayList<>();
                fieldErrors.add(fieldErrorMock);
                loginCallback.invalidForm(fieldErrors);
                return null;
            }
        }).when(loginUseCaseMock).login(any(LoginForm.class), any(LoginUseCase.LoginCallback.class));

        presenter.login(loginFormMock);

        verify(viewMock).hideKeyboard();
        verify(viewMock).showLoading();
        verify(viewMock).invalidForm(errorListArgumentCaptor.capture());
        verify(viewMock).hideLoading();
        verifyNoMoreInteractions(pageMock);
        assertThat(errorListArgumentCaptor.getValue().size(), is(1));
    }


    @Test
    public void whenApiErrorThenShowError(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                LoginUseCase.LoginCallback loginCallback = (LoginUseCase.LoginCallback)invocation.getArguments()[1];
                loginCallback.loginError("account not found");
                return null;
            }
        }).when(loginUseCaseMock).login(any(LoginForm.class), any(LoginUseCase.LoginCallback.class));

        presenter.login(loginFormMock);

        verify(viewMock).hideKeyboard();
        verify(viewMock).showLoading();
        verify(viewMock).clearErrors();
        verify(viewMock).hideLoading();
        verify(viewMock).loginError(errorArgumentCaptor.capture());
        verifyNoMoreInteractions(pageMock);
        assertThat(errorArgumentCaptor.getValue(), equalTo("account not found"));
    }


    @Test
    public void whenApiRespondAccountThenUserIsLogedIn(){
        final AccountBoundary accountBoundary = new AccountBoundary();
        accountBoundary.setEmail("a@gmail.com");
        accountBoundary.setToken("abcd1234");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                LoginUseCase.LoginCallback loginCallback = (LoginUseCase.LoginCallback)invocation.getArguments()[1];
                loginCallback.logedIn(accountBoundary);
                return null;
            }
        }).when(loginUseCaseMock).login(any(LoginForm.class), any(LoginUseCase.LoginCallback.class));

        presenter.login(loginFormMock);

        verify(viewMock).hideKeyboard();
        verify(viewMock).showLoading();
        verify(viewMock).clearErrors();
        verify(viewMock).hideLoading();
        verify(viewMock).logedIn();
        verify(pageMock).nextPage();
    }
}
