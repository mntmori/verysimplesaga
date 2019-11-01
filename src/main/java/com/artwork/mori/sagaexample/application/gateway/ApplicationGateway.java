package com.artwork.mori.sagaexample.application.gateway;

public interface ApplicationGateway {

    ApplicationGateway validate(Object command);
    Object handle(Object command);
}
