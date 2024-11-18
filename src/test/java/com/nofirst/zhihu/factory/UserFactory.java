package com.nofirst.zhihu.factory;

import com.nofirst.zhihu.mbg.model.User;
import com.nofirst.zhihu.security.AccountUser;

import java.util.Collections;

public class UserFactory {

    public static User createUser() {
        return new User(1, "user", "password");
    }

    public static AccountUser createAccountUser() {
        User user = createUser();
        AccountUser accountUser = new AccountUser(user.getId(), user.getName(), user.getPassword(), Collections.emptyList());
        return accountUser;
    }
}
