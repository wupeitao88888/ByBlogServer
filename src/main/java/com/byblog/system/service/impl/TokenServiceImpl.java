package com.byblog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byblog.system.dao.TokenMapper;
import com.byblog.system.model.Token;
import com.byblog.system.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Override
    public int saveOrUpdateToken(Token token) {
        boolean save = saveOrUpdate(token);
        if (save) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int updateToken(Token token) {
        boolean save = updateById(token);
        if (save) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public Token selectTokenByID(String userid) {
        QueryWrapper<Token> qt = new QueryWrapper<>();
        qt.eq(Token.USER_ID, userid);
        List<Token> list = list(qt);
        if (list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public Token selectTokensByToken(String token) {
        QueryWrapper<Token> qt = new QueryWrapper<>();
        qt.eq(Token.TOKEN, token);
        List<Token> list = list(qt);
        if (list == null || list.size() == 0)
            return null;
        return list.get(0);
    }
}
