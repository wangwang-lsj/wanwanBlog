package com.wanwan.springboot.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author：玩玩
 * @since：2024/3/15 22:25
 * @description:
 */
@Data
public class PageUtil {
    private long pageNum;

    private long pageSize;

    private long totalCount;

    private List<T> list;

    public PageUtil(IPage<T> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
    }

    public PageUtil() {
    }

}
