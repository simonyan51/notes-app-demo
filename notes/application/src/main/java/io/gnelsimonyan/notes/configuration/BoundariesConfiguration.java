/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 20:15
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.configuration;

import io.gnelsimonyan.notes.InfrastructureConfiguration;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.usecases.FindUserNoteUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        InfrastructureConfiguration.class
})
public class BoundariesConfiguration {
}
