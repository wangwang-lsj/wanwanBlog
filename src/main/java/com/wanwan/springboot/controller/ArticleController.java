package com.wanwan.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.annotation.AuthAccess;
import com.wanwan.springboot.pojo.po.UserArticleLike;
import com.wanwan.springboot.mapper.ArticleMapper;
import com.wanwan.springboot.service.ICommentService;
import com.wanwan.springboot.service.IUserArticleLikeService;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.IArticleService;
import com.wanwan.springboot.pojo.po.Article;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章(博客） 前端控制器
 * </p>
 *
 * @author wanwan
 * @since 2024-03-08
 */
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Resource
    private IArticleService articleService;
    @Resource
    private ICommentService commentService;
    @AuthAccess
    @GetMapping("/page")
    public Result queryPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String title,
                       @RequestParam(defaultValue = "") String description,
                       @RequestParam(defaultValue = "") String username,
                       @RequestParam(defaultValue = "") String categoryName,
                       @RequestParam(defaultValue = "") String orderTarget,
                       @RequestParam(defaultValue = "") String order
    ) {
        return Result.success(articleService.pageByCondition(pageNum,pageSize,title,description,username,categoryName,orderTarget,order));
    }

    @AuthAccess
    @GetMapping("/hots")
    public Result queryHots(@RequestParam Integer pageNum,
                          @RequestParam Integer pageSize) {
        return Result.success(articleService.pageHotArticle(pageNum,pageSize));
    }
    @AuthAccess
    @GetMapping("/relations")
    public Result queryRelations(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam Integer categoryId,
                               @RequestParam Integer articleId
    ) {
        return Result.success(articleService.pageRelatedArticle(pageNum,pageSize,categoryId,articleId));
    }

    @AuthAccess
    @GetMapping("/{id}/all")
    public Result queryById(@PathVariable Integer id) {
        return Result.success(articleService.getArticleAll(id));
    }


    @AuthAccess
    @GetMapping("/{articleId}/{userId}")
    public Result queryLike(@PathVariable Integer articleId,@PathVariable Integer userId){
        return Result.success(articleService.getLikeRelation(articleId,userId));
    }

    @AuthAccess
    @GetMapping("/statistics")
    public Result queryStatistics(){
        Map<String,Object> map = new HashMap<>();
        map.put("articleCount",articleService.count());
        map.put("todayRead",5);//暂时先这样
        map.put("commentCount",commentService.count());
        return Result.success(map);
    }

    // 更新
    @PutMapping
    public Result modify(@RequestBody Article article) {
        return Result.success(articleService.updateArticle(article));
    }

    @PostMapping()
    public Result create(@RequestBody Article article) {
        return Result.success(articleService.saveArticle(article));
    }
    @PutMapping("/likes")
    public Result updateLike(@RequestParam Integer articleId,
                                @RequestParam Integer userId,
                                @RequestParam Boolean isLike
    ){
        articleService.likeOrDislike(articleId,userId,isLike);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        articleService.removeArticle(id);
        return Result.success();
    }

    @DeleteMapping()
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        articleService.removeArticles(ids);
        return Result.success();
    }
    @AuthAccess
    @PatchMapping("/{id}")
    public Result updateReadCount(@PathVariable Integer id){
        articleService.updateReadCount(id);
        return Result.success();
    }
    @PatchMapping("/{id}/{homeShow}")
    public Result updateHomeShow(@PathVariable Integer id,@PathVariable Boolean homeShow){
        return Result.success(articleService.updateHomeShow(id,homeShow));
    }



}

