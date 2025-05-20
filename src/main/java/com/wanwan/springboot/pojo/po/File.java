package com.wanwan.springboot.pojo.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author：玩玩
 * @since：2024/2/2 21:45
 * @description:
 */
@Data
@TableName("file")
@Getter
@Setter
public class File {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("文件名")
    private String name;
    @ApiModelProperty("文件类型")
    private String type;
    @ApiModelProperty("文件大小")
    private Long size;
    @ApiModelProperty("文件路径")
    private String url;
    @ApiModelProperty("文件MD5")
    private String md5;
    @ApiModelProperty("是否删除")
    private Boolean isDelete;
    @ApiModelProperty("是否启用")
    private Boolean enable;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;
}
