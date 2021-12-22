package com.suhao.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.suhao.oledu.R;
import com.suhao.oledu.handler.exception.MyException;
import com.suhao.vod.service.VodService;
import com.suhao.vod.util.InitialVodClient;
import com.suhao.vod.util.VodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vod/video")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        return R.pass().data("videoId",videoId);
    }

    @DeleteMapping("/removeVideo/{id}")
    public R removeVideo(@PathVariable String id){
        try {
            DefaultAcsClient client = InitialVodClient.initVodClient(VodUtil.ACCESS_KEY_ID, VodUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.pass();
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"Failed to delete the videos");
        }
    }

    @DeleteMapping("/batchDelete")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.batchRemoveVideos(videoIdList);
        return R.pass();
    }

    @GetMapping("/getAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            DefaultAcsClient client = InitialVodClient.initVodClient(VodUtil.ACCESS_KEY_ID, VodUtil.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.pass().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new MyException(20001,"Failed to get Authorization");
        }
    }

}
