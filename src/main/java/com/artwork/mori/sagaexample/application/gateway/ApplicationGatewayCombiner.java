package com.artwork.mori.sagaexample.application.gateway;

import com.artwork.mori.sagaexample.application.command.ApproveResourceCreation;
import com.artwork.mori.sagaexample.application.command.CommandHandler;
import com.artwork.mori.sagaexample.application.command.PutResource;
import com.artwork.mori.sagaexample.application.model.dto.ResourceDto;
import com.artwork.mori.sagaexample.application.validator.Validator;
import com.artwork.mori.sagaexample.application.validator.ValidatorResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationGatewayCombiner implements ApplicationGateway.Combiner {


    private static final Map<Class, CommandHandler> COMMAND_HANDLERS = new HashMap<>();
    private static final Map<Class, Validator> VALIDATORS = new HashMap<>();

    private final CommandHandler<PutResource, ResourceDto> putResourceCommandHandlerResourceDtoCommandHandler;
    private final CommandHandler<ApproveResourceCreation, Void> approveResourceCreationVoidCommandHandler;
    private final Validator<ApproveResourceCreation> approveResourceCreationValidator;

    private ApplicationGatewayCombiner(CommandHandler<PutResource, ResourceDto> putCommandHandler,
                                       CommandHandler<ApproveResourceCreation, Void> approveResourceCreationVoidCommandHandler,
                                       Validator<ApproveResourceCreation> approveResourceCreationValidator) {
        this.putResourceCommandHandlerResourceDtoCommandHandler = putCommandHandler;
        this.approveResourceCreationVoidCommandHandler = approveResourceCreationVoidCommandHandler;
        this.approveResourceCreationValidator = approveResourceCreationValidator;
        commandHandlers();
        validators();
    }

    private void commandHandlers() {
        COMMAND_HANDLERS.put(PutResource.class, putResourceCommandHandlerResourceDtoCommandHandler);
        COMMAND_HANDLERS.put(ApproveResourceCreation.class, approveResourceCreationVoidCommandHandler);
    }


    private void validators() {
        VALIDATORS.put(ApproveResourceCreation.class, approveResourceCreationValidator);
    }

    @Override
    public ApplicationGateway.Combiner validate(Object command) {
        Validator validator = VALIDATORS.get(command.getClass());
        ValidatorResult validatorResult = validator.valid(command);
        if (validatorResult.isValid()) {
            return this;
        } else {
            throw new RuntimeException("Validation error");
        }
    }

    @Override
    public Object then(Object command) {
        CommandHandler commandHandler = COMMAND_HANDLERS.get(command.getClass());
        return commandHandler.apply(command);
    }
}