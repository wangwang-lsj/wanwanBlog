package com.wanwan.springboot.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @TableName slideshow
 */
@TableName(value ="slideshow")
@Data
@Getter
@Setter
public class Slideshow implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("轮播图名")
    private String name;
    @ApiModelProperty("图片路径")
    private String url;
    @ApiModelProperty("排序")
    private Integer sortNum;
    @ApiModelProperty("是否启用")
    private Boolean enable;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

}