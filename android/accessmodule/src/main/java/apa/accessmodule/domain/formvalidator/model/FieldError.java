package apa.accessmodule.domain.formvalidator.model;

/**
 * Created by alberto on 10/1/16.
 */
public class FieldError {


    private int reference;
    private int error;

    public FieldError() {}


    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
