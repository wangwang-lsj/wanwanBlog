package com.wanwan.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
@Getter
@Setter
@ApiModel(value = "leave_word", description = "")
public class LeaveWord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("留言人名称")
    private String nickName;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("留言内容")
    private String content;

    @ApiModelProperty("回复人ID")
    private Integer replyUserId;

    @TableField(exist = false)
    private String replyUserName;

    @ApiModelProperty("是否回复")
    private Boolean replied;

    @ApiModelProperty("回复内容")
    private String replyContent;

    @ApiModelProperty("是否展示")
    private Boolean enable;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("回复时间")
    private Date replyTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;
}
