package com.artwork.mori.sagaexample.application.handler.command;

public interface CommandHandler<C, R> {

    R apply(C command);
}
