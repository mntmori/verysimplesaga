package com.artwork.mori.sagaexample.adapter.primary.restapi;

import com.artwork.mori.sagaexample.application.command.ApproveResourceCreation;
import com.artwork.mori.sagaexample.application.gateway.ApplicationGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ResourceController {

    private final ApplicationGateway applicationGateway;

    public ResourceController(ApplicationGateway applicationGateway) {
        this.applicationGateway = applicationGateway;
    }

    /**
     * For easy e2e testing
     */
    @GetMapping(path = "/api/v1/resource/sign/creation", produces = APPLICATION_JSON_VALUE)
    public void sign() {
        ApproveResourceCreation approveResourceCreation = ApproveResourceCreation.of();
        applicationGateway
                //We can validate command here, can be helpfully when more sophisticated validation is needed
                .validate(approveResourceCreation)
                .handle(approveResourceCreation);
    }
}
