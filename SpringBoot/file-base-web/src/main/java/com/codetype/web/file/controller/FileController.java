package com.codetype.web.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/files")
@Api(tags = "文件中心")
public class FileController {
    private static final Logger log = LoggerFactory.getLogger(FileController.class);
    @Value("${nginx.nginxUrl}")
    private String nginxUrl;
    @Value("${file.filePath}")
    private String filePath;


    @RequestMapping(value="/oss",method = RequestMethod.POST)
    @ApiOperation(value = "文件上传")
    @Validated
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "图片", required = true,
                    dataType = "MultipartFile",dataTypeClass = MultipartFile.class,paramType = "query"),
            @ApiImplicitParam(dataType = "String",name = "token", value = "token(默认30min过期)",required = true),
    })
    public String fileOSS(@RequestParam("file") @RequestPart MultipartFile file) throws IOException {
        //原始文件名称
        String fileName = file.getOriginalFilename();
        //获取后缀
        String extension ="."+ FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新文件夹名称
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = format.format(new Date());
        String newFileName = newDate+ UUID.randomUUID().toString().replace("-", "") + extension;
        //生产日期目录
        String property = System.getProperty("user.dir");
        File dateDir = new File(property+filePath);
        if (!dateDir.exists())dateDir.mkdirs();
        //文件上传
        file.transferTo(new File(dateDir,newFileName));
//        return nginxUrl+newFileName ;
        String imgUrl = nginxUrl+"/api/article/list/img/";
        return imgUrl+newFileName ;
    }
    @ApiOperation(value = "文件下载")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "文件名",dataType = "String",name = "fileName",required = true),
            @ApiImplicitParam(value = "token(默认30min过期)",dataType = "String",name = "token",required = true)
    })
    @PostMapping("/download")
    public void download(String fileName,HttpServletResponse response ) throws IOException{
        //1.去指定文件夹下载文件
        String property = System.getProperty("user.dir");
        File file = new File(property+filePath, fileName);
        //2.将文件读取为输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        //3.给浏览器指定为下载类型
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        //4.获取响应输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //使用工具类将输入流复制给输出流
        FileCopyUtils.copy(fileInputStream, outputStream);
    }
}
