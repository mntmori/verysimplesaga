package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import com.artwork.mori.sagaexample.tool.saga.SagaManager;

public class SimpleSagaManager implements SagaManager<ApproveResourceCreationData> {

    @Override
    public SagaInstance<ApproveResourceCreationData> newInstance() {
        return new ApproveResourceCreationSaga();
    }
}
