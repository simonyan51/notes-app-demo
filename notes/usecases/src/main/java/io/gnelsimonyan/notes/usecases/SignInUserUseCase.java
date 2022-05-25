/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 12:12
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.usecases;

import io.gnelsimonyan.notes.common.Assert;
import io.gnelsimonyan.notes.boundaries.input.SignInUserInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SignInUserParams;
import io.gnelsimonyan.notes.boundaries.output.FindUserOutputBoundary;

public class SignInUserUseCase implements SignInUserInputBoundary {
    private final FindUserOutputBoundary findUserOutputBoundary;

    public SignInUserUseCase(final FindUserOutputBoundary findUserOutputBoundary) {
        this.findUserOutputBoundary = findUserOutputBoundary;
    }

    @Override
    public boolean signIn(final SignInUserParams signInUserParams) {
        Assert.notNull(signInUserParams, "SignIn user parameters should be provided");
        Assert.notEmpty(signInUserParams.email(), "Email must be provided");
        Assert.notEmpty(signInUserParams.password(), "Password must be provided");


        return false;
    }
}
