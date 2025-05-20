package com.wanwan.springboot.pojo.dto;

import lombok.Data;

/**
 * @author：玩玩
 * @since：2024/3/8 21:26
 * @description:
 */
@Data
public class MyRequestBody {
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private String title;
    private String description;
    private String userName;
    private String categoryName;
    private String orderTarget;
    private String order;
}
