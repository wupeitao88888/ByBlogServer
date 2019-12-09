package com.byblog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byblog.system.model.Contents;

public interface ContentsService extends IService<Contents> {
    Contents selectContentsByID(String cid);
}
