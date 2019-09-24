package com.artwork.mori.sagaexample.application.handler.command;


import com.artwork.mori.sagaexample.application.command.ApproveResourceCreation;
import com.artwork.mori.sagaexample.application.saga.ApproveResourceCreationData;
import com.artwork.mori.sagaexample.application.saga.SimpleSagaManager;
import com.artwork.mori.sagaexample.tool.saga.SagaInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApproveResourceCreationCommandHandler implements CommandHandler<ApproveResourceCreation, Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApproveResourceCreationCommandHandler.class);

    @Override
    public Void apply(ApproveResourceCreation command) {
        ApproveResourceCreationData sagaData = new ApproveResourceCreationData();

        SagaInstance<ApproveResourceCreationData> sagaInstance = new SimpleSagaManager().newInstance();
        ApproveResourceCreationData creationData = sagaInstance.start(sagaData);

        LOGGER.info("Saga staus: {}", creationData);
        return null;
    }
}
