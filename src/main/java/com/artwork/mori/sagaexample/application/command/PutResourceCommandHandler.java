package com.artwork.mori.sagaexample.application.command;

import com.artwork.mori.sagaexample.application.model.dto.ResourceDto;
import org.springframework.stereotype.Component;

@Component
public class PutResourceCommandHandler implements CommandHandler<PutResource, ResourceDto> {

    @Override
    public ResourceDto apply(final PutResource command) {
        return null;
    }

}
