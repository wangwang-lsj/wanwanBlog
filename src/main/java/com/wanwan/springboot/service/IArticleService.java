package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.pojo.po.Article;
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

    int updateArticle(Article article);

    boolean updateHomeShow(Integer id, Boolean homeShow);

    Page<Article> pageHotArticle(Integer pageNum, Integer pageSize);

    Page<Article> pageRelatedArticle(Integer pageNum, Integer pageSize, Integer categoryId, Integer articleId);

    Article getArticle(Integer id);

    void removeArticle(Integer id);

    void removeArticles(List<Integer> ids);

    Page<Article> pageByCondition(Integer pageNum, Integer pageSize, String title, String description, String username, String categoryName, String orderTarget, String order);

    void updateReadCount(Integer id);
    boolean getLikeRelation(Integer articleId, Integer userId);
}
