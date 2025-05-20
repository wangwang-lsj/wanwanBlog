package com.wanwan.springboot.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author：玩玩
 * @since：2024/2/16 19:35
 * @description:
 */
@TableName("role_menu")
@Data
@Getter
@Setter
public class RoleMenu {
    @ApiModelProperty("角色ID")
    private Integer roleId;
    @ApiModelProperty("菜单ID")
    private Integer menuId;
}
