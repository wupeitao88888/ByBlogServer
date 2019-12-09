package com.byblog.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byblog.common.JsonResult;
import com.byblog.common.utils.StringUtil;
import com.byblog.system.dao.ContentsMapper;
import com.byblog.system.model.Contents;
import com.byblog.system.model.Token;
import com.byblog.system.model.User;
import com.byblog.system.service.ContentsService;
import com.byblog.system.service.TokenService;
import com.byblog.system.service.UserService;
import com.byblog.system.vo.ContentsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(value = "博客管理", tags = "blog")
@RestController
@RequestMapping("v1/blog")
public class BlogController {


    private Logger logger = LoggerFactory.getLogger("UserController");

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ContentsMapper contentsMapper;
    @Autowired
    private ContentsService contentsService;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "博客列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "密钥", required = false, dataType = "String", paramType = "form", defaultValue = ""),
            @ApiImplicitParam(name = "keywords", value = "搜索内容", required = false, dataType = "String", paramType = "form", defaultValue = ""),
            @ApiImplicitParam(name = "pagecount", value = "数量", required = false, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "String", paramType = "form"),
    })
    @PostMapping(value = "get-blog-list")
    public JsonResult getBlogList(String token, String keywords, String page, String pagecount) {

        if (StringUtil.isBlank(page)) {
            page = "1";
        }
        if (StringUtil.isBlank(pagecount)) {
            pagecount = "20";
        }
        System.out.println("page:" + page + "/pagecount:" + pagecount);
        List<ContentsVO> contentsList = new ArrayList<>();
        if (StringUtil.isBlank(token) && StringUtil.isBlank(keywords)) {

            Map<String, Object> m = new HashMap<>();
            Page<ContentsVO> pages = new Page<>(StringUtil.parseInt(page), StringUtil.parseInt(pagecount));
            contentsList.addAll(contentsMapper.selectBlogAll(m, pages));
            return JsonResult.ok("").put("data", contentsList);
        } else if (StringUtil.isBlank(token)) {
            Map<String, Object> m = new HashMap<>();
            Page<ContentsVO> pages = new Page<>(StringUtil.parseInt(page), StringUtil.parseInt(pagecount));
            contentsList.addAll(contentsMapper.selectBlogAll(m, pages));
            return JsonResult.ok("").put("data", contentsList);
        } else if (StringUtil.isBlank(keywords)) {
            Token tokenBean = tokenService.selectTokensByToken(token);
            Map<String, Object> m = new HashMap<>();
            m.put("userid", tokenBean.getUserid());
            Page<ContentsVO> pages = new Page<>(StringUtil.parseInt(page), StringUtil.parseInt(pagecount));
            contentsList.addAll(contentsMapper.selectBlogUser(m, pages));
            return JsonResult.ok("").put("data", contentsList);
        } else {
            Token tokenBean = tokenService.selectTokensByToken(token);
            Map<String, Object> m = new HashMap<>();
            m.put("userid", tokenBean.getUserid());
            m.put("keywords", keywords);
            Page<ContentsVO> pages = new Page<>(StringUtil.parseInt(page), StringUtil.parseInt(pagecount));
            contentsList.addAll(contentsMapper.selectBlogSreach(m, pages));
        }
        return JsonResult.ok("").put("data", contentsList);
    }


    @ApiOperation(value = "博客详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "博客id", required = false, dataType = "String", paramType = "form", defaultValue = ""),
    })
    @PostMapping(value = "get-blog-info")
    public JsonResult getBlogInfo(String cid) {
        if (StringUtil.isBlank(cid)) {
            return JsonResult.error("请传入博客ID");
        }
        Contents contents = contentsService.selectContentsByID(cid);
        User user = userService.getUserById(contents.getUserid() + "");

        Map<String,String> map=new HashMap<>();

        map.put("userid",user.getUserid()+"");
        map.put("sex",user.getSex());
        map.put("nickname",user.getNickname());
        map.put("avatar",user.getAvatar());

        Map<String,Object> maps=new HashMap<>();

        maps.put("blog",contents);
        maps.put("user",map);
        return JsonResult.ok("").put("data", maps);
    }


}
