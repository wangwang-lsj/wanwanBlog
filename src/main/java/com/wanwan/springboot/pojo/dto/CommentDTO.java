package com.wanwan.springboot.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentDTO {
    private String avatarUrl; // 用户头像地址
    private String nickname; // 用户昵称
    private String replyUserNickname; // 回复的是哪个用户(ta的昵称)
    private String commentContent; // 评论内容
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; // 评论时间
    private Boolean isLike; // 是否点赞
    private Integer likeNum; // 点赞量
    private Integer replyTotalCount; // 一级评论下的回复数量
    private Integer replyCommentId; // 对那条评论进行的回复（对一级评论作回复, 不记录该replyCommentId）
    private Integer id; // 评论id
    private Integer userId; // 评论人id
    private Integer parentId; // 父级评论id(一级评论为null)
    private Integer replyUserId; // 回复用户id
    private Integer currentUserId;  // 当前用户id
    private List<CommentDTO> children; // 一级评论下的所有回复

}
