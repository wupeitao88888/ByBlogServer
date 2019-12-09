package com.byblog.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.byblog.system.model.Contents;
import com.byblog.system.vo.ContentsVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ContentsMapper  extends BaseMapper<Contents> {

    /****
     *
     * 查询所有用户的博客
     *
     * @param m  查询条件
     * @param page  分页
     * @return
     */
    @Select("SELECT b_contents.*,b_user.* FROM b_contents,b_user ORDER BY 'createtime' DESC")
    List<ContentsVO> selectBlogAll(Map<String,Object> m, Page<ContentsVO> page);

    /****
     *  查询特定用户端博客
     * @param m
     * @param page
     * @return
     */
    @Select("SELECT b_contents.*,b_user.* FROM b_contents,b_user WHERE USERID='m.userid' ORDER BY 'createtime' DESC")
    List<ContentsVO> selectBlogUser(Map<String,Object> m, Page<ContentsVO> page);

    /****
     *
     * 搜索博客
     * @param m
     * @param page
     * @return
     */
    @Select("SELECT b_contents.*,b_user.* FROM b_contents,b_user WHERE USERID='m.userid' LIKE 'm.keywords' ORDER BY 'createtime' DESC")
    List<ContentsVO> selectBlogSreach(Map<String,Object> m, Page<ContentsVO> page);


}
