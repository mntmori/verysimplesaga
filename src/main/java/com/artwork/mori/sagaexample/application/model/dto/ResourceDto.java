package com.artwork.mori.sagaexample.application.model.dto;

import lombok.Data;

@Data
public class ResourceDto {

    private final String message;

    public ResourceDto(String message) {
        this.message = message;
    }

    public static ResourceDto of(final String message) {
        return new ResourceDto(message);
    }
}
