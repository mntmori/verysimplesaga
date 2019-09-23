package com.artwork.mori.sagaexample.application.gateway;

public interface ApplicationGateway {

    Object just(Object command);
    Combiner validate(Object command);

    interface Combiner {
        Combiner validate(Object command);
        Object then(Object command);
    }
}
