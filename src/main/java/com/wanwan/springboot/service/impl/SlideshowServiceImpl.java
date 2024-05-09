package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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


    @Override
    public List<Slideshow> sliderList() {
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_num");
        return list();
    }

    @Override
    public List<Slideshow> enableSliderList() {
        QueryWrapper<Slideshow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", true);
        return list(queryWrapper);
    }
    @Override
    public Boolean saveOrUpdateSlider(Slideshow slideshow) {
        return saveOrUpdate(slideshow);
    }

    @Override
    public Boolean removeSliderById(Integer id) {
        return removeById(id);
    }

    @Override
    public Boolean removeSliderByIds(List<Integer> ids) {
        return removeBatchByIds(ids);
    }

    @Override
    public Boolean enableSlider(Slideshow slideshow) {
        UpdateWrapper<Slideshow> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", slideshow.getId());
        updateWrapper.set("enable", slideshow.getEnable());
        return update(updateWrapper);
    }
}




