package apa.accessmodule.domain.formvalidator.model;

/**
 * Created by alberto on 10/1/16.
 */
public class Field {


    private int reference;
    private String content;

    public Field() {}

    public Field(int reference, String content) {
        this.reference = reference;
        this.content = content;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
