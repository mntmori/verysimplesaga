package com.artwork.mori.sagaexample.application.command;

public interface CommandHandler<C, R> {

    R apply(C command);
}
