package com.byblog.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.byblog.system.model.User;
import sun.security.util.Password;

import java.util.List;

public interface UserService extends IService<User> {
    int getUserByName(String username);

    int setSaveUser(User user);

    User getUser(String username);

    User getUserById(String userid);
}
