package com.byblog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byblog.system.model.Token;

public interface TokenService extends IService<Token> {

    int saveOrUpdateToken(Token token);

    int updateToken(Token token);

    Token selectTokenByID(String userid);

    Token selectTokensByToken(String token);
}


