package com.artwork.mori.sagaexample.application.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class ValidatorResult {

    private final boolean valid;
}
