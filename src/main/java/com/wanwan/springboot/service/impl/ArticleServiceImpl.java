package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.mapper.CommentMapper;
import com.wanwan.springboot.mapper.UserCommentLikeMapper;
import com.wanwan.springboot.pojo.po.*;
import com.wanwan.springboot.mapper.ArticleMapper;
import com.wanwan.springboot.mapper.UserArticleLikeMapper;
import com.wanwan.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.utils.JWTUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章(博客） 服务实现类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    UserArticleLikeMapper userArticleLikeMapper;
    @Resource
    UserCommentLikeMapper userCommentLikeMapper;

    @Override
    public Page<Article> pageByCondition(Integer pageNum, Integer pageSize, String title, String description, String username, String categoryName, String orderTarget, String order) {
        return articleMapper.selectArticlePageByCondition(new Page<>(pageNum, pageSize), title, description, username, categoryName, orderTarget,order);
    }
    @RedisUpdate(keys = {Constants.HOME_ARTICLES})
    @Override
    public boolean saveArticle(Article article) {
        User currentUser = JWTUtils.getCurrentUser();
        article.setAuthorId(currentUser.getId());
        return articleMapper.insert(article) != 0;
    }

    @Override
    public Article getArticleAll(Integer id) {
        return articleMapper.selectArticleById(id);
    }


    @Transactional
    @Override
    public void likeOrDislike(Integer articleId, Integer userId, Boolean isLike) {
        if(isLike){
            QueryWrapper<UserArticleLike> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_Id",userId);
            queryWrapper.eq("article_Id",articleId);
            articleMapper.updateArticleLikesById(articleId,-1);
            userArticleLikeMapper.delete(queryWrapper);
        }else {
            UserArticleLike userArticleLike = new UserArticleLike();
            userArticleLike.setArticleId(articleId);
            userArticleLike.setUserId(userId);
            articleMapper.updateArticleLikesById(articleId,+1);
            userArticleLikeMapper.insert(userArticleLike);
        }
    }
    @RedisCache(key = Constants.HOME_ARTICLES,clazz = Article.class,isArray = true)
    @Override
    public List<Article> listHomeArticle() {
        return articleMapper.selectArticleByHomeShow();
    }

    @RedisUpdate(keys = {Constants.HOME_ARTICLES})
    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateById(article);
    }

    @RedisUpdate(keys = {Constants.HOME_ARTICLES})
    @Override
    public boolean updateHomeShow(Integer id, Boolean homeShow) {
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("home_show",homeShow);
        return update(updateWrapper);
    }
    @RedisCache(key = Constants.ARTICLE_HOTS,expire = 8)
    @Override
    public Page<Article> pageHotArticle(Integer pageNum, Integer pageSize) {
        return page(new Page<>(pageNum, pageSize), new QueryWrapper<Article>().orderByDesc("likes"));
    }

    @Override
    public Page<Article> pageRelatedArticle(Integer pageNum, Integer pageSize, Integer categoryId, Integer articleId) {
        return page(new Page<>(pageNum, pageSize), new QueryWrapper<Article>().eq("category_id",categoryId).ne("id",articleId).orderByDesc("read_count"));
    }
    @RedisCache(key = Constants.ARTICLE_ID,clazz = Article.class,isId = true)
    @Override
    public Article getArticle(Integer id) {
        return getById(id);
    }
    @RedisUpdate(keys = {Constants.ARTICLE},isPrefix = true)
    @Transactional
    @Override
    public void removeArticle(Integer id) {
        //删除文章评论点赞
        List<Object> commentObjs = commentMapper.selectObjs(new QueryWrapper<Comment>().eq("article_id", id).select("id"));
        if(commentObjs.size()!=0){
            List<Integer> commentIds = commentObjs.stream()
                    .map(obj -> ((Number) obj).intValue())
                    .collect(Collectors.toList());
            userCommentLikeMapper.delete(new QueryWrapper<UserCommentLike>().in("comment_id", commentIds));
        }
        //删除文章评论
        commentMapper.delete(new QueryWrapper<Comment>().eq("article_id",id));
        //删除文章点赞
        userArticleLikeMapper.delete(new QueryWrapper<UserArticleLike>().eq("article_id",id));
        // 删除文章
        removeById(id);
    }
    @RedisUpdate(keys = {Constants.ARTICLE},isPrefix = true)
    @Transactional
    @Override
    public void removeArticles(List<Integer> ids) {
        for (int id: ids) {
            removeArticle(id);
        }
    }

    @Override
    public void updateReadCount(Integer id) {
        articleMapper.updateArticleReadCountById(id,1);
    }

    @Override
    public boolean getLikeRelation(Integer articleId, Integer userId) {
        QueryWrapper<UserArticleLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        queryWrapper.eq("article_Id",articleId);
        return userArticleLikeMapper.selectOne(queryWrapper)!=null;
    }
}
