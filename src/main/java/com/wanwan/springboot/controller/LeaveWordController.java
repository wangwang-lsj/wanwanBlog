package com.wanwan.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.annotation.AuthAccess;
import com.wanwan.springboot.entity.LeaveWord;
import com.wanwan.springboot.utils.IpUtil;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.ILeaveWordService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
@RestController
@RequestMapping("/api/leavewords")
public class LeaveWordController {
    @Resource
    private ILeaveWordService leaveWordService;


    // @GetMapping("/page")
    // public Result page(@RequestParam Integer pageNum,
    //                    @RequestParam Integer pageSize) {
    //         Map<String, Object> map = new HashMap<>();
    //         map.put("records", leaveWordService.getByPage(new Page<>(pageNum, pageSize)));
    //         map.put("total", leaveWordService.count());
    //         return Result.success(map);
    // }
    @AuthAccess
    @GetMapping("/page")
    public Result page(@RequestHeader(name = "Wan-Source", required = false) String WanSource,
                       @RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String nickName
    ) {
        // System.out.println(WanSource);
        if (Objects.equals(WanSource, "manage")) {
            return Result.success(leaveWordService.pageLeaveWord(pageNum,pageSize,nickName,null));
        }
        return Result.success(leaveWordService.pageAbleLeaveWord(pageNum,pageSize,nickName,1));
    }

    // 新增
    @AuthAccess
    @PostMapping()
    public Result leaveMessage(@RequestBody LeaveWord leaveWord, HttpServletRequest request) {
        leaveWord.setIp(IpUtil.getIpAddr(request));
        return Result.success(leaveWordService.saveLeaveWord(leaveWord));
    }

    @PatchMapping()
    public Result show(@RequestBody LeaveWord leaveWord) {
        return Result.success(leaveWordService.updateShow(leaveWord));
    }

    @PutMapping()
    public Result reply(@RequestBody LeaveWord leaveWord) {
        leaveWord.setReplyTime(DateUtil.date());
        return Result.success(leaveWordService.reply(leaveWord));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return Result.success(leaveWordService.removeLeaveWord(id));
    }

    @DeleteMapping()
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(leaveWordService.removeLeaveWords(ids));
    }

}

