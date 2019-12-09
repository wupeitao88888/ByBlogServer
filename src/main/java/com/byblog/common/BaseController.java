package com.byblog.common;


import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 * Created by wangfan on 2018-02-22 上午 11:29.
 */
public class BaseController {

    /**
     * 获取当前登录的userId
     */
    public Integer getLoginUserId(HttpServletRequest request) {
//        Token token = getLoginToken(request);
//        return token == null ? null : Integer.parseInt(token.getUserId());
        return 100;
    }

//    public Token getLoginToken(HttpServletRequest request) {
//
//        return SubjectUtil.getToken(request);
//    }


    /***
     * 获取公参里面的UUID
     * @param request
     * @return
     */
    public String getPublicParametersUUID(HttpServletRequest request) {
        return request.getParameter("uuid");
    }
    /***
     * 获取公参里面的UUID
     * @param request
     * @return
     */
    public String getPublicParametersType(HttpServletRequest request) {
        return request.getParameter("type");
    }
}
