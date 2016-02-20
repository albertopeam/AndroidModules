package apa.components.data.api.exception;

/**
 * Created by alberto on 20/2/16.
 */
public class LoginErrorException extends Exception {
    public LoginErrorException(String detailMessage) {
        super(detailMessage);
    }
}
