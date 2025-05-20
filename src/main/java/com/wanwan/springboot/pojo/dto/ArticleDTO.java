package com.wanwan.springboot.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/19  13:22
 */
public class ArticleDTO{
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("封面")
    private String cover;
    @ApiModelProperty("类别ID")
    private Integer categoryId;
    @ApiModelProperty("标签")
    private String tags;
    @ApiModelProperty("浏览量")
    private Integer readCount;
    @ApiModelProperty("喜欢数")
    private Integer likes;
    @ApiModelProperty("评论数量")
    private Integer commentCount;

}
