package com.wanwan.springboot.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章(博客）
 * </p>
 *
 * @author wanwan
 * @since 2024-03-08
 */
@Getter
@Setter
@ApiModel(value = "Article对象", description = "文章(博客）")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("封面")
    private String cover;

    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("作者ID")
    private Integer authorId;

    @ApiModelProperty("浏览量")
    private Integer readCount;

    @ApiModelProperty("喜欢数")
    private Integer likes;

    @ApiModelProperty("类别ID")
    private Integer categoryId;
    @ApiModelProperty("主页显示")
    private Boolean homeShow;
    @TableField(exist = false)
    @ApiModelProperty("作者用户名")
    private String userName;

    @TableField(exist = false)
    @ApiModelProperty("类别名称")
    private String categoryName;

    @TableField(exist = false)
    @ApiModelProperty("评论数量")
    private Integer commentCount;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发布日期")
    private Date publicDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新日期")
    private Date updateDate;

}
