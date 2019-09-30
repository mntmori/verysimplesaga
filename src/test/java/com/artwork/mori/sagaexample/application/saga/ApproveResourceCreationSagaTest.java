package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.application.port.AuthorizationServerFacade;
import com.artwork.mori.sagaexample.application.port.DraftRepository;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ApproveResourceCreationSagaTest {

    private final AuthorizationServerFacade authorizationServerFacade = Mockito.mock(AuthorizationServerFacade.class);
    private final DraftRepository draftRepository = Mockito.mock(DraftRepository.class);

    @Test
    public void testSaga() {
        //given
        final ApproveResourceCreationData approveResourceCreationData = new ApproveResourceCreationData();
        SagaInstance<ApproveResourceCreationData> signDraftSagaDataSagaInstance =
                new ApproveResourceCreationSaga(authorizationServerFacade, draftRepository);
        //when
        Mockito.when(authorizationServerFacade.authorize()).thenReturn(true);
        //and
        signDraftSagaDataSagaInstance.start(approveResourceCreationData);
        //then
        Assert.assertTrue(approveResourceCreationData.isDraftCreated());
        Assert.assertTrue(approveResourceCreationData.isDraftSigned());
    }

    @Test
    public void testSagaFailure() {
        //given
        final ApproveResourceCreationData approveResourceCreationData = new ApproveResourceCreationData();
        SagaInstance<ApproveResourceCreationData> signDraftSagaDataSagaInstance =
                new ApproveResourceCreationSaga(authorizationServerFacade, draftRepository);
        //when
        Mockito.when(authorizationServerFacade.authorize()).thenReturn(false);
        //and
        signDraftSagaDataSagaInstance.start(approveResourceCreationData);
        //then
        Assert.assertTrue(approveResourceCreationData.isHasError());
    }
}