package com.artwork.mori.sagaexample.tool.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class SagaInstance<E extends SagaData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SagaInstance.class);

    public abstract SagaDefinition<E> getSagaDefinition();

    public E start(final E initialState) {
        SagaDefinition<E> sagaDefinition = getSagaDefinition();
        for (SagaStep<E> sagaStep : sagaDefinition.getSagaSteps()) {
            try {
                sagaStep.getAction().accept(initialState);
            } catch (Exception e) {
                compensate(initialState, sagaDefinition, sagaStep);
                break;
            }
        }
        return initialState;
    }

    private void compensate(final E initialState,
                            final SagaDefinition<E> sagaDefinition,
                            final SagaStep<E> failedStep) {
        LOGGER.error("Step {} failed!", failedStep.getName());
        List<SagaStep<E>> sagaSteps = sagaDefinition.getSagaSteps();
        ArrayList<SagaStep<E>> reverseSagaSteps = reverseSagaSteps(sagaSteps);

        int failedStepIndex = reverseSagaSteps.indexOf(failedStep);
        ListIterator<SagaStep<E>> sagaStepListIterator = reverseSagaSteps.listIterator(failedStepIndex);

        while (sagaStepListIterator.hasNext()) {
            SagaStep<E> next = sagaStepListIterator.next();
            LOGGER.error("Calling {} compensation method!", next.getName());
            next.getCompensation().accept(initialState);
        }
    }

    private ArrayList<SagaStep<E>> reverseSagaSteps(List<SagaStep<E>> sagaSteps) {
        ArrayList<SagaStep<E>> clone = new ArrayList<>(sagaSteps);
        Collections.reverse(clone);
        return clone;
    }
}
