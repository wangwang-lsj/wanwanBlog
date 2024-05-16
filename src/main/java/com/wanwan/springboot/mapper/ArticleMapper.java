package com.wanwan.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 文章(博客） Mapper 接口
 * </p>
 *
 * @author wanwan
 * @since 2024-03-08
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    Page<Article> selectArticlePageByCondition(Page<Article> page, @Param("title") String title, @Param("description") String description, @Param("userName") String userName, @Param("categoryName") String categoryName, @Param("orderTarget") String orderTarget, @Param("order") String order);

    Article selectArticleById(@Param("id")Integer id);


    @Update("update article set likes = likes + #{num} where id = #{id}")
    void updateArticleLikesById(@Param("id")Integer id, @Param("num")Integer num);

    @Update("update article set read_count = read_count + #{num} where id = #{id}")
    void updateArticleReadCountById(@Param("id")Integer id, @Param("num")Integer num);


    List<Article> selectArticleByHomeShow();
}
