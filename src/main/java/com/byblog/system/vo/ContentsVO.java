package com.byblog.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ContentsVO {
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
    @ApiModelProperty(value = "帖子内容")
    private Integer userid=0;

    @Builder.Default
    @ApiModelProperty(value = "昵称")
    private String nickname="";  // 昵称

    @Builder.Default
    @ApiModelProperty(value = "头像")
    private String avatar="";  // 头像

    @ApiModelProperty(value = "性别")
    private String sex;  // 性别

}
