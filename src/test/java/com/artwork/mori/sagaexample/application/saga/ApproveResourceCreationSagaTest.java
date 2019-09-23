package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import org.junit.Assert;
import org.junit.Test;

public class ApproveResourceCreationSagaTest {

    @Test
    public void testSaga() {
        final ApproveResourceCreationData approveResourceCreationData = new ApproveResourceCreationData();
        SagaInstance<ApproveResourceCreationData> signDraftSagaDataSagaInstance = new ApproveResourceCreationSaga();
        signDraftSagaDataSagaInstance.start(approveResourceCreationData);
        Assert.assertTrue(approveResourceCreationData.isDraftCreated());
        Assert.assertTrue(approveResourceCreationData.isDraftSigned());
    }

    @Test
    public void testSagaFailure() {
        final ApproveResourceCreationData approveResourceCreationData = new ApproveResourceCreationData();
        SagaInstance<ApproveResourceCreationData> signDraftSagaDataSagaInstance = new ApproveResourceCreationSaga();
        signDraftSagaDataSagaInstance.start(approveResourceCreationData);
        Assert.assertTrue(approveResourceCreationData.isHasError());
    }
}