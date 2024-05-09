package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.entity.Article;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.entity.UserArticleLike;
import com.wanwan.springboot.mapper.ArticleMapper;
import com.wanwan.springboot.mapper.UserArticleLikeMapper;
import com.wanwan.springboot.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public void saveArticle(Article article) {
        User currentUser = JWTUtils.getCurrentUser();
        article.setAuthorId(currentUser.getId());
        articleMapper.insert(article);
    }

    @Override
    public Article getOneAllById(Integer id) {
        return articleMapper.selectOneAllById(id);
    }

    @Transactional
    @Override
    public void likeOrDislike(Integer articleId, Integer userId, Boolean isLike) {
        if(isLike){
            QueryWrapper<UserArticleLike> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_Id",userId);
            queryWrapper.eq("article_Id",articleId);
            articleMapper.updateArticleLikes(articleId,-1);
            userArticleLikeMapper.delete(queryWrapper);
        }else {
            UserArticleLike userArticleLike = new UserArticleLike();
            userArticleLike.setArticleId(articleId);
            userArticleLike.setUserId(userId);
            articleMapper.updateArticleLikes(articleId,+1);
            userArticleLikeMapper.insert(userArticleLike);
        }
    }

    @Override
    public List<Article> getHomeArticle() {
        return articleMapper.selectHomeArticle();
    }
}
