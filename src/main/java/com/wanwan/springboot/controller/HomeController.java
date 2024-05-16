package com.wanwan.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.annotation.AuthAccess;
import com.wanwan.springboot.entity.Slideshow;
import com.wanwan.springboot.service.IArticleService;
import com.wanwan.springboot.service.ISlideshowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author：玩玩
 * @since：2024/3/12 17:59
 * @description:
 */
@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Resource
    ISlideshowService slideshowService;
    @Resource
    IArticleService articleService;
    @AuthAccess
    @GetMapping("/slider")
    public Result getAll(@RequestHeader(name = "Wan-Source", required = false) String WanSource){
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(WanSource, "manage")) {
            return Result.success(slideshowService.listSlider());
        }
        queryWrapper.eq("enable", true);
        return Result.success(slideshowService.listEnableSlider());
    }
    @AuthAccess
    @GetMapping("/articles")
    public Result getHomeArticles(){
        return Result.success(articleService.listHomeArticle());
    }
    @PostMapping("/slider")
    public Result saveOrUpdate(@RequestBody Slideshow slideshow){
        return Result.success(slideshowService.saveOrUpdateSlider(slideshow));
    }
    @DeleteMapping("/slider/{id}")
    public Result deleteById(@PathVariable Integer id){
        return Result.success(slideshowService.removeSlider(id));
    }
    @DeleteMapping("/slider")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(slideshowService.removeSliders(ids));
    }
    @PatchMapping("/slider")
    public Result show(@RequestBody Slideshow slideshow){
        return Result.success(slideshowService.updateSliderEnable(slideshow));
    }
}
