package com.artwork.mori.sagaexample.application.validator;

public interface Validator<C> {

    ValidatorResult valid(C command);
}
