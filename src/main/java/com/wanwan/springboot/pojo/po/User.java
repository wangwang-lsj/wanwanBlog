package com.wanwan.springboot.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author wanwan
 * @since 2024-01-22
 */
@Getter
@Setter
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("用户名")
    private String username;
    @JsonIgnore
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("头像路径")
    private String avatarUrl;
    @ApiModelProperty("角色ID")
    private Integer roleId;
    @TableField(exist = false)
    @ApiModelProperty("角色名")
    private String role;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("姓名")
    private String realName;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("生日")
    private Date birthday;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("最近登陆")
    private Date recentlyLanded;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

}
