package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.tool.saga.SagaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApproveResourceCreationData extends SagaData<ApproveResourceCreationData.ImmutableModel> {

    private boolean draftCreated;
    private boolean draftUpdated;
    private boolean draftSigned;
    private boolean hasError;

    public ApproveResourceCreationData() {
        super(ImmutableModel.of(false, false, false, false));
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    static class ImmutableModel {
        private final boolean draftCreated;
        private final boolean draftUpdated;
        private final boolean draftSigned;
        private final boolean hasError;
    }
}
