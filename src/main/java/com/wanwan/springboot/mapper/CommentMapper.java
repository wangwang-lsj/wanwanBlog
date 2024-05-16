package com.wanwan.springboot.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanwan.springboot.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanwan.springboot.entity.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 玩玩
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-03-14 18:02:20
* @Entity com.wanwan.springboot.entity.Comment
*/

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    IPage<CommentDTO> selectCommentPageByArticleId(@Param("page") IPage<Comment> page, @Param("articleId") Integer articleId, @Param("currentUserId") Integer currentUserId);

    List<CommentDTO> selectCommentPageByCommentId(@Param("commentId") Integer commentId, @Param("startIndex") Integer startIndex, @Param("count") Integer count, @Param("currentUserId") Integer currentUserId);

    CommentDTO selectCommentById(@Param("id") Integer id);

    void updateCommentLikeById(@Param("commentId") Integer commentId,@Param("num") Integer num);
    void insertLikeByCommentIdAndUserId(@Param("commentId") Integer commentId, @Param("userId")Integer userId);
    void deleteLikeByCommentIdAndUserId(@Param("commentId")Integer commentId, @Param("userId")Integer userId);

    Integer selectCommentCountByArticleId(@Param("articleId")Integer articleId);

    Boolean selectLikeByCommentIdAndUserId(@Param("commentId") Integer commentId, @Param("userId")Integer userId);

}




