package dev.aomegax.echoboard.be.model;


public enum EndUserType {
    ADMIN("ADMIN"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private final String type;

    EndUserType(String string) {
        type = string;
    }
}