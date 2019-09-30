package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.application.port.AuthorizationServerFacade;
import com.artwork.mori.sagaexample.application.port.DraftRepository;
import com.artwork.mori.sagaexample.domain.Draft;
import com.artwork.mori.sagaexample.tool.saga.SagaDefinition;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import com.artwork.mori.sagaexample.tool.saga.SagaStepFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApproveResourceCreationSaga extends SagaInstance<ApproveResourceCreationData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveResourceCreationSaga.class);

    private final SagaDefinition<ApproveResourceCreationData> sagaDefinition = new SagaDefinition<>();

    private final AuthorizationServerFacade authorizationServerFacade;
    private final DraftRepository draftRepository;

    ApproveResourceCreationSaga(AuthorizationServerFacade authorizationServerFacade, DraftRepository draftRepository) {
        this.authorizationServerFacade = authorizationServerFacade;
        this.draftRepository = draftRepository;
        defineScenario();
    }

    private void defineScenario() {
        sagaDefinition
                .step("create draft", this::createDraft, this::onCreateFailure)
                .participantStep("call authorization server", this::callAuthorizationServer, this::onCallingFailure)
                .step("update draft", this::updateDraft, this::onUpdateFailure);
    }

    @Override
    public SagaDefinition<ApproveResourceCreationData> getSagaDefinition() {
        return this.sagaDefinition;
    }

    private void createDraft(final ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.info("Creating draft!");
        Draft draft = new Draft();
        draftRepository.save(draft);
        approveResourceCreationData.setDraft(draft);
        approveResourceCreationData.setDraftCreated(true);
    }

    private void onCreateFailure(final ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftCreated(false);
        draftRepository.delete(approveResourceCreationData.getDraft());
        LOGGER.error("Creating draft step compensated - draft deleted");
    }

    private void callAuthorizationServer(final ApproveResourceCreationData approveResourceCreationData) {
        if (approveResourceCreationData.isDraftCreated()) {
            LOGGER.info("Calling authorization server...");
            boolean authorized = authorizationServerFacade.authorize();
            if (authorized) {
                approveResourceCreationData.setDraftSigned(true);
            } else {
                throw new SagaStepFailException();
            }
        }
    }

    private void onCallingFailure(final ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftUpdated(false);
        LOGGER.error("Authorization server call step compensated");
    }

    private void updateDraft(final ApproveResourceCreationData approveResourceCreationData) {
        LOGGER.info("Updating draft!");
        Draft draft = approveResourceCreationData.getDraft();
        draft.approve();
        draftRepository.save(draft);
        approveResourceCreationData.setDraftUpdated(true);
    }

    private void onUpdateFailure(final ApproveResourceCreationData approveResourceCreationData) {
        approveResourceCreationData.setHasError(true);
        approveResourceCreationData.setDraftUpdated(false);
        LOGGER.error("Updating draft step compensated");
    }

}
