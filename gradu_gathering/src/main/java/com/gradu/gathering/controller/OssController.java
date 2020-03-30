package com.gradu.gathering.controller;

import com.gradu.gathering.service.OssService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = ossService.upload(file);
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
