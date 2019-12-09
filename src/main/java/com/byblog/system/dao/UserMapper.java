package com.byblog.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byblog.system.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

}
