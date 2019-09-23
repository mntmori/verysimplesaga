package com.artwork.mori.sagaexample.application.gateway;

import org.springframework.stereotype.Component;

@Component
public class ResourceApplicationGateway implements ApplicationGateway {

    private final Combiner combiner;

    public ResourceApplicationGateway(Combiner combiner) {
        this.combiner = combiner;
    }

    @Override
    public Object just(Object command) {
        return combiner.then(command);
    }

    @Override
    public Combiner validate(Object command) {
        return combiner.validate(command);
    }

}
