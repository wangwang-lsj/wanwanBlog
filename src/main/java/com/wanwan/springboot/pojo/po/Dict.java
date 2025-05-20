package com.wanwan.springboot.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author：玩玩
 * @since：2024/2/16 13:52
 * @description:
 */
@TableName("dict")
@Data
@Getter
@Setter
public class Dict {

    @ApiModelProperty("名")
    private String name;
    @ApiModelProperty("值")
    private String value;
    @ApiModelProperty("类型")
    private String type;
}
