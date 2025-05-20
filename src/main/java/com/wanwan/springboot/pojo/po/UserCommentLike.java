package com.wanwan.springboot.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户评论关系表
 * @TableName user_comment_like
 */
@TableName(value ="user_comment_like")
@Data
@Getter
@Setter
public class UserCommentLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户ID")
    private Integer user_id;

    @ApiModelProperty("评论ID")
    private Integer comment_id;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

}