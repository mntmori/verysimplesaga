package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.application.port.AuthorizationServerFacade;
import com.artwork.mori.sagaexample.application.port.DraftRepository;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import com.artwork.mori.sagaexample.tool.saga.SagaManager;
import org.springframework.stereotype.Component;

@Component
public class ApproveResourceSagaManager implements SagaManager<ApproveResourceCreationData> {

    private final AuthorizationServerFacade authorizationServerFacade;
    private final DraftRepository draftRepository;

    public ApproveResourceSagaManager(AuthorizationServerFacade authorizationServerFacade,
                                      DraftRepository draftRepository) {
        this.authorizationServerFacade = authorizationServerFacade;
        this.draftRepository = draftRepository;
    }

    @Override
    public SagaInstance<ApproveResourceCreationData> newInstance() {
        return new ApproveResourceCreationSaga(authorizationServerFacade, draftRepository);
    }
}
