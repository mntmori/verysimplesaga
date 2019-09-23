package com.artwork.mori.sagaexample.application.command;


import com.artwork.mori.sagaexample.application.saga.ApproveResourceCreationData;
import com.artwork.mori.sagaexample.application.saga.ApproveResourceCreationSaga;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApproveResourceCreationCommandHandler implements CommandHandler<ApproveResourceCreation, Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveResourceCreationCommandHandler.class);

    @Override
    public Void apply(ApproveResourceCreation command) {
        SagaInstance<ApproveResourceCreationData> sagaInstance = new ApproveResourceCreationSaga();
        ApproveResourceCreationData creationData = sagaInstance.start(new ApproveResourceCreationData());
        LOGGER.info("Saga staus: {}", creationData);
        return null;
    }
}
