package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanwan.springboot.entity.Slideshow;

import java.util.List;

/**
* @author 玩玩
* @description 针对表【slideshow】的数据库操作Service
* @createDate 2024-03-12 16:25:23
*/
public interface ISlideshowService extends IService<Slideshow> {

    List<Slideshow> sliderList();

    List<Slideshow> enableSliderList();

    Boolean saveOrUpdateSlider(Slideshow slideshow);

    Boolean removeSliderById(Integer id);

    Boolean removeSliderByIds(List<Integer> ids);

    Boolean enableSlider(Slideshow slideshow);

}
