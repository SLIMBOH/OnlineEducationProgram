package com.suhao.oledu.controller;


import com.suhao.oledu.R;
import com.suhao.oledu.entity.EduVideo;
import com.suhao.oledu.feign.VodService;
import com.suhao.oledu.handler.exception.MyException;
import com.suhao.oledu.service.EduVideoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/edu/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodService vodService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.pass();
    }

    @GetMapping("getVideoInfo/{videoId}")
    public R getChapterInfo(@PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.pass().data("video",eduVideo);
    }

    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return R.pass();
    }

    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {

        EduVideo video = eduVideoService.getById(id);

        String videoSourceId = video.getVideoSourceId();

        if(!StringUtils.isEmpty(videoSourceId)){
            R res = vodService.removeVideo(videoSourceId);
            if(res.getCode() == 20001){
                throw new MyException(20001, "Failed, Hystrix is invoked");
            }
        }

        eduVideoService.removeById(id);

        return R.pass();
    }

}

