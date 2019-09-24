package com.artwork.mori.sagaexample.tool.saga;

public interface SagaManager <T extends SagaData> {

    SagaInstance<T> newInstance();
}
