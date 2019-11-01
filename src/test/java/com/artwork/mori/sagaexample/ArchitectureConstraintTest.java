package com.artwork.mori.sagaexample;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.artwork.mori.sagaexample")
public class ArchitectureConstraintTest {

    @ArchTest
    ArchRule onionArchitectureTest = onionArchitecture()
            .applicationServices("..application..")
            .domainModels("..domain..")
            .adapter("primary-restapi", "..adapter.primary.restapi..")
            .adapter("secondary-authorizationserver", "..adapter.secondary.authorizationserver..")
            .adapter("secondary-draftrepository", "..adapter.secondary.repository.draft..");
}
