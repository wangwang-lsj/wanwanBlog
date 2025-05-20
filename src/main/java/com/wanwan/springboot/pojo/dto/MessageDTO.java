package com.wanwan.springboot.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/25  16:40
 */
@Data
public class MessageDTO {
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
