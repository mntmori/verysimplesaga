package com.artwork.mori.sagaexample.application.validator;

import com.artwork.mori.sagaexample.application.command.ApproveResourceCreation;
import org.springframework.stereotype.Component;

@Component
public class ApproveResourceCreationValidator implements Validator<ApproveResourceCreation> {

    @Override
    public ValidatorResult valid(ApproveResourceCreation command) {
        return ValidatorResult.of(true);
    }
}
