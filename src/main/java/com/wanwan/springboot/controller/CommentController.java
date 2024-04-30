package com.wanwan.springboot.controller;

import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.config.AuthAccess;
import com.wanwan.springboot.entity.Comment;
import com.wanwan.springboot.entity.dto.CommentDTO;
import com.wanwan.springboot.mapper.CommentMapper;
import com.wanwan.springboot.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author：玩玩
 * @since：2024/3/14 18:10
 * @description:
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Resource
    private CommentMapper commentMapper;
    @AuthAccess
    @GetMapping("/page")
    public Result getByArticleId(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam Integer articleId,
                                 @RequestParam Integer currentUserId
    ){

        return Result.success(commentService.getCommentListByPage(pageNum,pageSize,articleId,currentUserId));
    }
    @AuthAccess
    @GetMapping("/replies")
    public Result getReplies(@RequestParam Integer commentId,
                             @RequestParam Integer startIndex,
                             @RequestParam Integer count,
                             @RequestParam Integer currentUserId) {
        return Result.success(commentService.getReplyListByPage(commentId, startIndex,count,currentUserId));
    }
    @AuthAccess
    @GetMapping("/{articleId}")
    public Result getCountByArticleId(@PathVariable Integer articleId){
        return Result.success(commentMapper.getCountByArticleId(articleId));
    }

    @PostMapping()
    public Result addComment(@RequestBody Comment comment) {
        return Result.success(commentService.addComment(comment));
    }


    @PostMapping("/like/{commentId}/{userId}")
    public Result like(@PathVariable Integer commentId,@PathVariable Integer userId){
        commentService.like(commentId,userId);
        return Result.success();
    }
    @PostMapping("/dislike/{commentId}/{userId}")
    public Result disLike(@PathVariable Integer commentId,@PathVariable Integer userId){
        commentService.disLike(commentId,userId);
        return Result.success();
    }

}
