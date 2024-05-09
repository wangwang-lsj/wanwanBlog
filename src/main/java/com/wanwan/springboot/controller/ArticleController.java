package com.wanwan.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.config.AuthAccess;
import com.wanwan.springboot.entity.UserArticleLike;
import com.wanwan.springboot.entity.dto.MyRequestBody;
import com.wanwan.springboot.mapper.ArticleMapper;
import com.wanwan.springboot.mapper.CommentMapper;
import com.wanwan.springboot.service.ICommentService;
import com.wanwan.springboot.service.IUserArticleLikeService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.IArticleService;
import com.wanwan.springboot.entity.Article;

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
    private ArticleMapper articleMapper;
    @Resource
    private ICommentService commentService;
    @Resource
    private IUserArticleLikeService userArticleLikeService;

    @AuthAccess
    @GetMapping("/page")
    public Result page(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String title,
                       @RequestParam(defaultValue = "") String description,
                       @RequestParam(defaultValue = "") String username,
                       @RequestParam(defaultValue = "") String categoryName,
                       @RequestParam(defaultValue = "") String orderTarget,
                       @RequestParam(defaultValue = "") String order
    ) {
        return Result.success(articleMapper.selectAllByPage(new Page<>(pageNum, pageSize), title, description, username, categoryName, orderTarget,order));
    }

    @AuthAccess
    @GetMapping("/hots")
    public Result getHots(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize) {
        return Result.success(articleService.page(new Page<>(pageNum, pageSize), new QueryWrapper<Article>().orderByDesc("likes")));
    }
    @AuthAccess
    @GetMapping("/relations")
    public Result getRelations(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam Integer categoryId,
                               @RequestParam Integer articleId
    ) {
        return Result.success(articleService.page(new Page<>(pageNum, pageSize), new QueryWrapper<Article>().eq("category_id",categoryId).ne("id",articleId).orderByDesc("read_count")));
    }


    @GetMapping()
    public Result getAll() {
        return Result.success(articleService.list());
    }
    @AuthAccess
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(articleService.getById(id));
    }


    @AuthAccess
    @GetMapping("/{id}/all")
    public Result getOneAll(@PathVariable Integer id) {
        return Result.success(articleService.getOneAllById(id));
    }




    @AuthAccess
    @GetMapping("/{articleId}/{userId}")
    public Result getLike(@PathVariable Integer articleId,@PathVariable Integer userId){
        QueryWrapper<UserArticleLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_Id",userId);
        queryWrapper.eq("article_Id",articleId);
        return Result.success(userArticleLikeService.list(queryWrapper));
    }

    @AuthAccess
    @GetMapping("/statistics")
    public Result statistics(){
        Map<String,Object> map = new HashMap<>();
        map.put("articleCount",articleService.count());
        map.put("todayRead",5);//暂时先这样
        map.put("commentCount",commentService.count());
        return Result.success(map);
    }

    // 新增或者更新
    @PutMapping
    public Result save(@RequestBody Article article) {
        articleService.saveOrUpdate(article);
        return Result.success();
    }

    @PostMapping()
    public Result create(@RequestBody Article article) {
        articleService.saveArticle(article);
        return Result.success();
    }
    @PutMapping("/likes")
    public Result LikeOrDisLike(@RequestParam Integer articleId,
                                @RequestParam Integer userId,
                                @RequestParam Boolean isLike
    ){
        articleService.likeOrDislike(articleId,userId,isLike);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        articleService.removeById(id);
        return Result.success();
    }

    @DeleteMapping()
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        articleService.removeByIds(ids);
        return Result.success();
    }
    @AuthAccess
    @PatchMapping("/{id}")
    public Result recordReadCount(@PathVariable Integer id){
        articleMapper.updateArticleReadCount(id,1);
        return Result.success();
    }
    @PatchMapping("/{id}/{homeShow}")
    public Result changeHomeShow(@PathVariable Integer id,@PathVariable Boolean homeShow){
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("home_show",homeShow);
        articleService.update(updateWrapper);
        return Result.success();
    }

}

