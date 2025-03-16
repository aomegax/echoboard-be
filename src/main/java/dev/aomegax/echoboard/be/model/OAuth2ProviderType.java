package dev.aomegax.echoboard.be.model;


public enum OAuth2ProviderType {
    GITHUB("GITHUB"),
    GOOGLE("GOOGLE");

    private final String type;

    OAuth2ProviderType(String string) {
        type = string;
    }
}