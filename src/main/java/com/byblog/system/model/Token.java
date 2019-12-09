package com.byblog.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.ws.soap.Addressing;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_usertoken")
@ApiModel(value="Token对象", description="用来存储登陆用户的token")
public class Token  extends Model<User> {

    @ApiModelProperty(value = "唯一标识，主键")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonSerialize(using= ToStringSerializer.class)
    private int id;

    @ApiModelProperty(value = "用户id")
    private Integer userid;

    @ApiModelProperty(value = "用户id")
    private String token;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;


    public static final String USER_ID = "userid";

    public static final String TOKEN = "token";

    public static final String CREATETIME = "createtime";

    public static final String UPDATETIME = "updatetime";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
