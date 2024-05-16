package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.entity.Slideshow;
import com.wanwan.springboot.service.ISlideshowService;
import com.wanwan.springboot.mapper.SlideshowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 玩玩
* @description 针对表【slideshow】的数据库操作Service实现
* @createDate 2024-03-12 16:25:23
*/
@Service
public class SlideshowServiceImpl extends ServiceImpl<SlideshowMapper, Slideshow> implements ISlideshowService {

    // @RedisCache(key = "home:slider",clazz = Slideshow.class,isArray = true)
    @Override
    public List<Slideshow> listSlider() {
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_num");
        return list();
    }

    @RedisCache(key = Constants.HOME_SLIDER,clazz = Slideshow.class,isArray = true)
    @Override
    public List<Slideshow> listEnableSlider() {
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", true);
        return list(queryWrapper);
    }

    @RedisUpdate(keys = {Constants.HOME_SLIDER})
    @Override
    public Boolean saveOrUpdateSlider(Slideshow slideshow) {
        return saveOrUpdate(slideshow);
    }

    @RedisUpdate(keys = {Constants.HOME_SLIDER})
    @Override
    public Boolean removeSlider(Integer id) {
        return removeById(id);
    }

    @RedisUpdate(keys = {Constants.HOME_SLIDER})
    @Override
    public Boolean removeSliders(List<Integer> ids) {
        return removeBatchByIds(ids);
    }

    @RedisUpdate(keys = {Constants.HOME_SLIDER})
    @Override
    public Boolean updateSliderEnable(Slideshow slideshow) {
        UpdateWrapper<Slideshow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", slideshow.getId());
        updateWrapper.set("enable", slideshow.getEnable());
        return update(updateWrapper);
    }
}




