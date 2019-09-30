package com.artwork.mori.sagaexample.application.saga;

import com.artwork.mori.sagaexample.domain.Draft;
import com.artwork.mori.sagaexample.tool.saga.SagaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApproveResourceCreationData implements SagaData<ApproveResourceCreationData.ImmutableModel> {

    private final ImmutableModel immutableModel;

    private boolean draftCreated;
    private boolean draftUpdated;
    private boolean draftSigned;
    private boolean hasError;
    private Draft draft;

    public ApproveResourceCreationData() {
        this.immutableModel = ImmutableModel.of(false, false, false, false);
    }

    @Override
    public ImmutableModel getImmutableModel() {
        return this.immutableModel;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
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
