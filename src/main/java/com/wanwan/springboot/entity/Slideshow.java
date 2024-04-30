package com.wanwan.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    private Integer id;

    private String name;
    private String url;

    private Integer sortNum;

    private Boolean enable;
    private Date updateTime;
    private Date createTime;

    private static final long serialVersionUID = 1L;

}