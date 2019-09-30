package com.artwork.mori.sagaexample.tool.saga;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class SagaDefinition<S> {

    private final List<SagaStep<S>> sagaSteps = new LinkedList<>();

    public SagaDefinition<S> step(String name, Consumer<S> step, Consumer<S> compensation) {
        sagaSteps.add(new SagaStep<>(name, step, compensation));
        return this;
    }

    public SagaDefinition<S> participantStep(String name, Consumer<S> step, Consumer<S> compensation) {
        sagaSteps.add(new SagaStep<>(name, step, compensation));
        return this;
    }

    List<SagaStep<S>> getSagaSteps() {
        return Collections.unmodifiableList(sagaSteps);
    }
}
