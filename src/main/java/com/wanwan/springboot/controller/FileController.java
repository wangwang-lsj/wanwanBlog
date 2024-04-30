package com.wanwan.springboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.config.AuthAccess;
import com.wanwan.springboot.entity.Files;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.mapper.FileMapper;
import com.wanwan.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @author：玩玩
 * @since：2024/2/2 19:51
 * @description:文件上传相关接口
 */
@RestController
@RequestMapping("/api/files")
public class FileController {
    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Value("${server.ip}")
    private String serverIp;
    @Resource
    private FileMapper fileMapper;




    /**
     * 文件上传
     * @param file
     * @return
     */
    @AuthAccess
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file)throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        // 存储到磁盘
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID= uuid + StrUtil.DOT + type;
        // files/文件名
        File uploadFile = new File(fileUploadPath+fileUUID);
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        String md5 = SecureUtil.md5(file.getInputStream());
        Files files = getFileByMd5(md5);
        String url;
        if(files != null){
            url = files.getUrl();

        }else{
            file.transferTo(uploadFile);
            url = "http://"+serverIp+":9090/api/files/"+fileUUID;
        }

        // 存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return Result.success(url);
    }

    @AuthAccess
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response)throws IOException{
        File downloadFile = new File(fileUploadPath+fileUUID);
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileUUID,"UTF-8"));
        response.setContentType("application/octet-stream");
        // 读取文件字节流
        os.write(FileUtil.readBytes(downloadFile));
        os.flush();
        os.close();
    }

    /**
     * 分页查询接口
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Result page(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String name
    ) {
        IPage<Files> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",false);
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByDesc("id");
        IPage<Files> userIPage = fileMapper.selectPage(page,queryWrapper);
        return  Result.success(userIPage);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Files file) {
        return Result.success(fileMapper.updateById(file));
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        Files files = fileMapper.selectById(id);
        files.setIsDelete(true);
        return Result.success(fileMapper.updateById(files));
    }
    @DeleteMapping()
    public Result deleteBatch(@RequestBody List<Integer> ids){
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        List<Files> files = fileMapper.selectList(queryWrapper);
        for(Files file: files){
            file.setIsDelete(true);
            fileMapper.updateById(file);
        }
        return Result.success();
    }

    @PutMapping()
    public Result editFileById(@RequestBody Files file){
        return Result.success(fileMapper.updateById(file));
    }



    /**
     * 通过md5获取文件
     * @param md5
     * @return
     */
    private Files getFileByMd5(String md5){
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size()==0?null:filesList.get(0);
    }


}
