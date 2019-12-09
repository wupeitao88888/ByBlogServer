package com.byblog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byblog.system.dao.UserMapper;
import com.byblog.system.model.User;
import com.byblog.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /***
     *
     *  1 、有一个 2、存在多个 3、不存在
     *
     * @param username
     * @return
     */
    @Override
    public int getUserByName(String username) {
        QueryWrapper<User> queryWrapper =
                new QueryWrapper<User>()
                        .eq(User.USERNAME, username);
        List<User> userList = list(queryWrapper);

        if (userList == null || userList.size() == 0)
            return 3;
        if (userList.size() > 1) {
            return 2;
        }
        return 1;
    }

    /****
     *
     * 1、保存失败 0、保存成功
     * @param user
     * @return
     */
    @Override
    public int setSaveUser(User user) {
        boolean save = save(user);
        if (save) {
            return 0;
        }
        return 1;
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper =
                new QueryWrapper<User>()
                        .eq(User.USERNAME, username);
        List<User> userList = list(queryWrapper);

        if (userList == null || userList.size() == 0)
            return null;

        return userList.get(0);
    }

    @Override
    public User getUserById(String userid) {
        QueryWrapper<User> queryWrapper =
                new QueryWrapper<User>()
                        .eq(User.USER_ID, userid);
        List<User> userList = list(queryWrapper);

        if (userList == null || userList.size() == 0)
            return null;

        return userList.get(0);
    }
}
