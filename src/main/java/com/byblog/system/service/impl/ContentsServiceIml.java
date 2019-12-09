package com.byblog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byblog.system.dao.ContentsMapper;
import com.byblog.system.model.Contents;
import com.byblog.system.model.Token;
import com.byblog.system.service.ContentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContentsServiceIml extends ServiceImpl<ContentsMapper, Contents> implements ContentsService {


    @Override
    public Contents selectContentsByID(String cid) {
        QueryWrapper<Contents> qt = new QueryWrapper<>();
        qt.eq(Contents.CID, cid);
        List<Contents> list = list(qt);
        if (list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

}
