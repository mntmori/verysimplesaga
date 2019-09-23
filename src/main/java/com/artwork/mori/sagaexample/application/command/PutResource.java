package com.artwork.mori.sagaexample.application.command;

public class PutResource {

    private final boolean draft;

    private PutResource(boolean draft) {
        this.draft = draft;
    }

    public static PutResource of(boolean draft) {
        return new PutResource(draft);
    }

    public boolean isADraft() {
        return draft;
    }
}
