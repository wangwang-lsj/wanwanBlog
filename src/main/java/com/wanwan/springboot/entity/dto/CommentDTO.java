package com.wanwan.springboot.entity.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {

    private Integer replyTotalCount; // 一级评论下的回复数量

    private List<CommentDTO> children; // 一级评论下的所有回复

    private String nickname; // 用户昵称

    private String replyUserNickname; // 回复的是哪个用户(ta的昵称)

    private Integer replyCommentId; // 对那条评论进行的回复（对一级评论作回复, 不记录该replyCommentId）

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 父级评论id(顶级评论为null)
     */
    private Integer parentId;

    /**
     * 回复用户id
     */
    private Integer replyUserId;

    /**
     * 评论人id
     */
    private Integer userId;
    /**
     * 评论内容
     */
    private String commentContent;



    /**
     * 点赞量
     */
    private Integer currentUserId;
    private Integer likeNum;

    private Boolean isLike; // 是否点赞

    private String avatarUrl; // 用户头像地址

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
