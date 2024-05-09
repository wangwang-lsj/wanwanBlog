package com.wanwan.springboot.service;

import com.wanwan.springboot.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章(博客） 服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-08
 */
public interface IArticleService extends IService<Article> {

    void saveArticle(Article article);

    Article getOneAllById(Integer id);

    void likeOrDislike(Integer articleId, Integer userId, Boolean isLike);

    List<Article> getHomeArticle();
}
