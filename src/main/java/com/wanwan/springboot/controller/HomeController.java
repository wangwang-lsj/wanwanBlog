package com.wanwan.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.config.AuthAccess;
import com.wanwan.springboot.entity.Slideshow;
import com.wanwan.springboot.mapper.SlideshowMapper;
import com.wanwan.springboot.service.ISlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
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
    SlideshowMapper slideshowMapper;
    @AuthAccess
    @GetMapping("/slider")
    public Result getAll(@RequestHeader(name = "Wan-Source", required = false) String WanSource){
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        if (Objects.equals(WanSource, "manage")) {
            queryWrapper.orderByAsc("sort_num");
            return Result.success(slideshowService.list(queryWrapper));
        }
        queryWrapper.eq("enable", true);
        return Result.success(slideshowService.list(queryWrapper));
    }
    @PostMapping("/slider")
    public Result saveOrUpdate(@RequestBody Slideshow slideshow){
        return Result.success(slideshowService.saveOrUpdate(slideshow));
    }
    @DeleteMapping("/slider/{id}")
    public Result deleteById(@PathVariable Integer id){
        return Result.success(slideshowService.removeById(id));
    }
    @DeleteMapping("/slider")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(slideshowService.removeBatchByIds(ids));
    }
    @PatchMapping("/slider")
    public Result show(@RequestBody Slideshow slideshow){
        UpdateWrapper<Slideshow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", slideshow.getId());
        updateWrapper.set("enable", slideshow.getEnable());
        return Result.success(slideshowService.update(updateWrapper));
    }


}
