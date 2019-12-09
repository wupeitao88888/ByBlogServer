package com.byblog.system.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.NonFinal;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("b_user")
@ApiModel(value="User对象", description="所有注册用户的信息")
@JsonIgnoreProperties({"password","createtime","updatetime"})
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {
    private static final long serialVersionUID = 242146703513492331L;

    @ApiModelProperty(value = "唯一标识，主键")
    @TableId(value = "userid", type = IdType.AUTO)
    private Integer userid;  // 用户id

    @ApiModelProperty(value = "账号")
    private String username;  // 账号

    @ApiModelProperty(value = "密码")
    private String password;  // 密码

    @Builder.Default
    @ApiModelProperty(value = "昵称")
    private String nickname="";  // 昵称

    @Builder.Default
    @ApiModelProperty(value = "头像")
    private String avatar="";  // 头像

    @ApiModelProperty(value = "性别")
    private String sex;  // 性别

    @Builder.Default
    @ApiModelProperty(value = "手机号")
    private String phone="";  // 手机号

    @Builder.Default
    @ApiModelProperty(value = "邮箱")
    private String email="";  // 邮箱

    @Builder.Default
    @ApiModelProperty(value = " 邮箱是否验证，0未验证，1已验证")
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer emailverified=0;  // 邮箱是否验证，0未验证，1已验证

    @Builder.Default
    @ApiModelProperty(value = "真实姓名")
    private String truename="";  // 真实姓名

    @Builder.Default
    @ApiModelProperty(value = "身份证号")
    private String idcard="";  // 身份证号

    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;  // 出生日期

    @Builder.Default
    @ApiModelProperty(value = "部门id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer departmentid=0; // 部门id

    @Builder.Default
    @ApiModelProperty(value = "状态，0正常，1冻结")
    @JsonSerialize(using= ToStringSerializer.class)
    private Integer state=0;  // 状态，0正常，1冻结

    @ApiModelProperty(value = "注册时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;  // 注册时间

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatetime;  // 修改时间



    public static final String USER_ID = "userid";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NICKNAME = "nick_name";

    public static final String AVATAR = "avatar";

    public static final String SEX = "sex";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String EMAILVERFIED = "emailverified";

    public static final String TRUENAME = "true_name";

    public static final String IDCARD = "id_card";

    public static final String BIRTHDAY = "birthday";

    public static final String DEPARTMENT_ID = "departmentid";

    public static final String STATE = "state";

    public static final String CREATETIME = "createtime";

    public static final String UPDATETIME = "updatetime";

    @Override
    protected Serializable pkVal() {
         return this.userid;
    }
}
