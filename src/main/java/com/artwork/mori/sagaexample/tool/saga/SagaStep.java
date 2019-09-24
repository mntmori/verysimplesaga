package com.artwork.mori.sagaexample.tool.saga;

import java.util.Objects;
import java.util.function.Consumer;

public class SagaStep<E> {

    private final String name;
    private final Consumer<E> action;
    private final Consumer<E> compensation;

    SagaStep(final String name, final Consumer<E> action, final Consumer<E> compensation) {
        Objects.requireNonNull(name, "Saga step name must be specified");
        this.name = name;
        this.action = action;
        this.compensation = compensation;
    }

    String getName() {
        return name;
    }

    Consumer<E> getAction() {
        return action;
    }

    Consumer<E> getCompensation() {
        return compensation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SagaStep<?> sagaStep = (SagaStep<?>) o;
        return name.equals(sagaStep.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
