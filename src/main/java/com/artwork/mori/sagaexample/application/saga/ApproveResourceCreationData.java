package com.artwork.mori.sagaexample.application.saga;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApproveResourceCreationData {
    private boolean draftCreated;
    private boolean draftSigned;
    private boolean hasError;
}
