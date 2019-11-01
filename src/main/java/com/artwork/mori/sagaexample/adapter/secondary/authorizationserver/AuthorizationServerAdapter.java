package com.artwork.mori.sagaexample.adapter.secondary.authorizationserver;

import com.artwork.mori.sagaexample.application.port.AuthorizationServerFacade;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationServerAdapter implements AuthorizationServerFacade {

    @Override
    public boolean authorize() {
        return true;
    }
}
