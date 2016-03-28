package com.example;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "response")
public class FooResponse {
    private int success;
    private List<Transaction> successfull;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(final int success) {
        this.success = success;
    }

    public List<Transaction> getSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(final List<Transaction> successfull) {
        this.successfull = successfull;
    }
}
