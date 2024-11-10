package com.galega.customer.domain.exception;

public class CustomerNotFound extends RuntimeException {

    private final String identifierKey;
    private final String identifierValue;

    public CustomerNotFound(String key, String value) {
        super("Customer not found with " + key + " equals to " + value);
        this.identifierKey = key;
        this.identifierValue = value;
    }

    public String getIdentifierKey() {
        return identifierKey;
    }

    public String getIdentifierValue() {
        return identifierValue;
    }
}
