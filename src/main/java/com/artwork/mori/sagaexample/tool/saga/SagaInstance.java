package com.artwork.mori.sagaexample.tool.saga;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class SagaInstance<D extends SagaData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SagaInstance.class);

    public abstract SagaDefinition<D> getSagaDefinition();

    public D start(final D sagaData) {
        SagaDefinition<D> sagaDefinition = getSagaDefinition();
        for (SagaStep<D> sagaStep : sagaDefinition.getSagaSteps()) {
            try {
                sagaStep.getAction().accept(sagaData);
            } catch (Exception e) {
                compensate(sagaData, sagaDefinition, sagaStep);
                break;
            }
        }
        return sagaData;
    }

    private void compensate(final D sagaData,
                            final SagaDefinition<D> sagaDefinition,
                            final SagaStep<D> failedStep) {
        LOGGER.error("Step {} failed!", failedStep.getName());
        List<SagaStep<D>> sagaSteps = sagaDefinition.getSagaSteps();
        ArrayList<SagaStep<D>> reversedSagaSteps = reverseSagaSteps(sagaSteps);

        final int failedStepIndex = reversedSagaSteps.indexOf(failedStep);
        ListIterator<SagaStep<D>> reversedSagaStepsIterator = reversedSagaSteps.listIterator(failedStepIndex);

        while (reversedSagaStepsIterator.hasNext()) {
            final SagaStep<D> stepToCompensate = reversedSagaStepsIterator.next();
            LOGGER.error("Calling {} compensation method!", stepToCompensate.getName());
            stepToCompensate.getCompensation().accept(sagaData);
        }
    }

    private ArrayList<SagaStep<D>> reverseSagaSteps(List<SagaStep<D>> sagaSteps) {
        ArrayList<SagaStep<D>> clone = new ArrayList<>(sagaSteps);
        Collections.reverse(clone);
        return clone;
    }
}
