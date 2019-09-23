package com.artwork.mori.sagaexample.tool.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public abstract class SagaInstance<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SagaInstance.class);

    public abstract SagaDefinition<E> getSagaDefinition();

    public E start(E initialState) {
        SagaDefinition<E> sagaDefinition = getSagaDefinition();
        sagaDefinition.getSagaSteps()
                .stream()
                .forEach(eSagaStep -> {
                    try {
                        eSagaStep.getAction().accept(initialState);
                    } catch (Exception e) {
                        compensate(initialState, sagaDefinition, eSagaStep);
                    }

                });
        return initialState;
    }

    private void compensate(E initialState, SagaDefinition<E> sagaDefinition, SagaStep<E> eSagaStep) {
        LOGGER.error("Step " + eSagaStep.getName() + " failed!");
        Iterator<SagaStep<E>> sagaStepIterator = sagaDefinition.getSagaSteps().descendingIterator();
        while (sagaStepIterator.hasNext()) {
            SagaStep<E> compensationStep = sagaStepIterator.next();
            String name = compensationStep.getName();
            compensationStep.getCompensation().accept(initialState);
            System.out.println(name);
        }
    }
}
