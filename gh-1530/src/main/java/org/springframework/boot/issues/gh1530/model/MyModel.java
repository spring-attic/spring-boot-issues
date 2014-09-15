package org.springframework.boot.issues.gh1530.model;

/**
 * An example model.
 * @author Pato Istvan <istvan.pato@vanio.hu>
 */
public class MyModel {

    private String myValue;

    public MyModel(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public String toString() {
        return "MyModel{" + "myValue=" + myValue + '}';
    }

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }

}
