package com.artwork.mori.sagaexample.application.port;

import com.artwork.mori.sagaexample.domain.Draft;

public interface DraftRepository {

    void save(Draft draft);

    void delete(Draft draft);
}
