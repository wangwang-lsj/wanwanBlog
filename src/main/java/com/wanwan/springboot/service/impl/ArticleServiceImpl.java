package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.entity.Article;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.entity.UserArticleLike;
import com.wanwan.springboot.mapper.ArticleMapper;
import com.wanwan.springboot.mapper.UserArticleLikeMapper;
import com.wanwan.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.utils.JWTUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    UserArticleLikeMapper userArticleLikeMapper;
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
    public boolean saveOrUpdateArticle(Article article) {
        return saveOrUpdate(article);
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
    @Override
    public boolean removeArticle(Integer id) {
        return removeById(id);
    }
    @RedisUpdate(keys = {Constants.ARTICLE},isPrefix = true)
    @Override
    public boolean removeArticles(List<Integer> ids) {
        return removeBatchByIds(ids);
    }
}
