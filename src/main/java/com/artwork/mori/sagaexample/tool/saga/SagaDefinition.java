package com.artwork.mori.sagaexample.tool.saga;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class SagaDefinition<E> {

    private final List<SagaStep<E>> sagaSteps = new LinkedList<>();

    public SagaDefinition<E> step(String name, Consumer<E> step, Consumer<E> compensation) {
        sagaSteps.add(new SagaStep<>(name, step, compensation));
        return this;
    }

    public SagaDefinition<E> participantStep(String name, Consumer<E> step, Consumer<E> compensation) {
        sagaSteps.add(new SagaStep<>(name, step, compensation));
        return this;
    }

    List<SagaStep<E>> getSagaSteps() {
        return Collections.unmodifiableList(sagaSteps);
    }
}
