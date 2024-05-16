package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    boolean saveArticle(Article article);

    Article getArticleAll(Integer id);


    void likeOrDislike(Integer articleId, Integer userId, Boolean isLike);

    List<Article> listHomeArticle();

    boolean saveOrUpdateArticle(Article article);

    boolean updateHomeShow(Integer id, Boolean homeShow);

    Page<Article> pageHotArticle(Integer pageNum, Integer pageSize);

    Page<Article> pageRelatedArticle(Integer pageNum, Integer pageSize, Integer categoryId, Integer articleId);

    Article getArticle(Integer id);

    boolean removeArticle(Integer id);

    boolean removeArticles(List<Integer> ids);
}
