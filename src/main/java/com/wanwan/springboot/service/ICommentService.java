package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanwan.springboot.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanwan.springboot.entity.dto.CommentDTO;

import java.util.List;

/**
* @author 玩玩
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-03-14 18:02:20
*/
public interface ICommentService extends IService<Comment> {

    CommentDTO saveComment(Comment comment);
    IPage<CommentDTO> pageComment(Integer pageNum, Integer pageSize, Integer articleId, Integer currentUserId);

    void like(Integer commentId, Integer userId);
    void disLike(Integer commentId, Integer userId);


    List<CommentDTO> pageSecondComment(Integer commentId, Integer startIndex, Integer count, Integer currentUserId);

}
