package com.nofirst.zhihu.helper;

import com.nofirst.zhihu.mbg.model.User;
import org.assertj.core.api.AbstractAssert;

class UserAssert extends AbstractAssert<UserAssert, User> {

    UserAssert(User user) {
        super(user, UserAssert.class);
    }

    static UserAssert assertThat(User actual) {
        return new UserAssert(actual);
    }

    UserAssert hasRegistrationDate() {
        isNotNull();
        if (actual.getCreatedAt() == null) {
            failWithMessage(
                    "Expected user to have a registration date, but it was null"
            );
        }
        return this;
    }
}