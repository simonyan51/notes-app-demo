/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 20:26
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.user;

import io.gnelsimonyan.notes.boundaries.output.FindUserOutputBoundary;
import io.gnelsimonyan.notes.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserOutputBoundaryAdapter implements FindUserOutputBoundary {
    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
