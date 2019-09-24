package com.artwork.mori.sagaexample.tool.saga;

public abstract class SagaData<I> {

    private final I immutableModel;

    protected SagaData(I immutableModel) {
        this.immutableModel = immutableModel;
    }
}
