package apa.components.data.api.exception;

/**
 * Created by alberto on 20/2/16.
 */
public class LoginApiException extends Exception {


    private int code;


    public LoginApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public LoginApiException(int code) {
        super("");
        this.code = code;
    }


    public LoginApiException(String message) {
        super(message);
        this.code = -1;
    }


    public int getCode() {
        return code;
    }
}

