package com.byblog.system.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("b_contents")
@ApiModel(value="Contents对象", description="帖子详情")
@AllArgsConstructor
@NoArgsConstructor
public class Contents extends Model<Contents> {

    @ApiModelProperty(value = "唯一标识，主键")
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @Builder.Default
    @ApiModelProperty(value = "标题")
    private String title="";

    @Builder.Default
    @ApiModelProperty(value = "封面")
    private String thumbimg="";

    @Builder.Default
    @ApiModelProperty(value = "创建时间")
    private Date createtime=new Date();

    @Builder.Default
    @ApiModelProperty(value = "修改时间")
    private Date updatetime=new Date();

    @Builder.Default
    @ApiModelProperty(value = "类型：0、页面  1、帖子")
    private Integer type=1;

    @Builder.Default
    @ApiModelProperty(value = "状态：0、发表  1、下架")
    private Integer status=1;

    @Builder.Default
    @ApiModelProperty(value = "帖子语法：0、markdown ")
    private Integer fmttype=0;

    @Builder.Default
    @ApiModelProperty(value = "标签")
    private String tags="";

    @Builder.Default
    @ApiModelProperty(value = "点赞数量")
    private Integer hits=0;

    @Builder.Default
    @ApiModelProperty(value = "评论数量")
    private Integer commentsnum=0;

    @Builder.Default
    @ApiModelProperty(value = "浏览量")
    private Integer pageview=0;

    @Builder.Default
    @ApiModelProperty(value = "是否可以评论")
    private Integer allowcomment=0;

    @Builder.Default
    @ApiModelProperty(value = "帖子内容")
    private String content="";


    @Builder.Default
    @ApiModelProperty(value = "用户id")
    private Integer userid=0;

    public static final String CID = "cid";
    public static final String TITLE = "title";
    public static final String THUMBIMG = "thumbimg";
    public static final String CREATETIME = "createtime";
    public static final String UPDATETIME = "updatetime";
    public static final String TYPE = "type";

    public static final String STATUS = "status";
    public static final String FMTYPE = "fmttype";
    public static final String TAGS = "tags";

    public static final String HITS = "hits";
    public static final String COMMENTSNUM = "commentsnum";


    public static final String PAGE = "pageview";
    public static final String ALLOWCOMMENT = "allowcomment";
    public static final String CONTENT = "content";

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }
}
