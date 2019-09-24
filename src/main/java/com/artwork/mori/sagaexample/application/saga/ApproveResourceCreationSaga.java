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
                .step("create draft", this::createDraft, this::onCreateFailure)
                .step("update draft", this::updateDraft, this::onUpdateFailure)
                .participantStep("call orders", this::callOrders, this::onCallingFailure);
        return sagaDefinition;
    }

    private void createDraft(ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.info("Creating draft!");
        approveResourceCreationData.setDraftCreated(true);
    }

    private void updateDraft(ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.info("Updating draft!");
        approveResourceCreationData.setDraftUpdated(true);
    }

    private void callOrders(ApproveResourceCreationData approveResourceCreationData) {
        if (approveResourceCreationData.isDraftCreated()) {
            approveResourceCreationData.setDraftSigned(true);
            LOGGER.info("Calling orders...");
        }
    }

    private void onCreateFailure(ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftCreated(false);
        LOGGER.error("Creating draft step compensated");
    }

    private void onUpdateFailure(ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftUpdated(false);
        LOGGER.error("Updating draft step compensated");
    }

    private void onCallingFailure(ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftUpdated(false);
        LOGGER.error("Calling orders step compensated");
    }

}
