package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.tool.saga.SagaDefinition;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApproveResourceCreationSaga extends SagaInstance<ApproveResourceCreationData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveResourceCreationSaga.class);

    @Override
    public SagaDefinition<ApproveResourceCreationData> getSagaDefinition() {
        SagaDefinition<ApproveResourceCreationData> sagaDefinition = new SagaDefinition<>();
        sagaDefinition
                .step("create draft", this::createDraft, this::onFailure)
                .participantStep("call orders", this::callOrders, this::onFailure);
        return sagaDefinition;
    }

    private void createDraft(ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.info("Creating draft!");
        approveResourceCreationData.setDraftCreated(true);
    }

    private void callOrders(ApproveResourceCreationData approveResourceCreationData) {
        if (approveResourceCreationData.isDraftCreated()) {
            LOGGER.info("Calling orders...");
            approveResourceCreationData.setDraftSigned(true);
        }
    }

    private void onFailure(ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.error("Failure occurred!");
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftSigned(false);
        approveResourceCreationData.setDraftCreated(false);
    }

}
