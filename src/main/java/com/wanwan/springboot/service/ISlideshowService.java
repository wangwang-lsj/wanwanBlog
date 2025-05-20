package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wanwan.springboot.pojo.po.Slideshow;

import java.util.List;

/**
* @author 玩玩
* @description 针对表【slideshow】的数据库操作Service
* @createDate 2024-03-12 16:25:23
*/
public interface ISlideshowService extends IService<Slideshow> {

    List<Slideshow> listSlider();

    List<Slideshow> listEnableSlider();

    Boolean saveSlider(Slideshow slideshow);
    int updateSlider(Slideshow slideshow);

    Boolean removeSlider(Integer id);

    Boolean removeSliders(List<Integer> ids);

    Boolean updateSliderEnable(Slideshow slideshow);

}
