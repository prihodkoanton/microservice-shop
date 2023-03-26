package ru.effectivemobile.microserviceorder.core.exceptions;

public class OrganizationNotFoundException extends RuntimeException {

    public OrganizationNotFoundException(String message) {
        super(message);
    }
}
