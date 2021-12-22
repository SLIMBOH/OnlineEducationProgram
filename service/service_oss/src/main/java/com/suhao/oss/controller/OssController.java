package com.suhao.oss.controller;

import com.suhao.oledu.R;
import com.suhao.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/oss/file")
//@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping(value = "/upload")
    public R uploadOssFile(MultipartFile file) {

        String url = ossService.uploadFileAvatar(file);
        return R.pass().data("url",url);
    }
}
