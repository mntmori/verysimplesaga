package com.artwork.mori.sagaexample.tool.saga;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class SagaDefinition<E> {

    private final Deque<SagaStep<E>> sagaSteps = new LinkedList<>();

    public SagaDefinition<E> step(String name, Consumer<E> step, Consumer<E> compensation) {
        sagaSteps.addLast(new SagaStep<>(name, step, compensation));
        return this;
    }

    public SagaDefinition<E> participantStep(String name, Consumer<E> step, Consumer<E> compensation) {
        sagaSteps.addLast(new SagaStep<>(name, step, compensation));
        return this;
    }

    Deque<SagaStep<E>> getSagaSteps() {
        return sagaSteps;
    }
}
