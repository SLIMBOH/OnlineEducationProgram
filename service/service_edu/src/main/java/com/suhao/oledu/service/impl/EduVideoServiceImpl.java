package com.suhao.oledu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.oledu.entity.EduVideo;
import com.suhao.oledu.feign.VodService;
import com.suhao.oledu.mapper.EduVideoMapper;
import com.suhao.oledu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodService vodService;

    @Override
    public void deleteVideoByCourseId(String courseId) {

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_source_id");

        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);

        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideos.size(); i++) {
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
        }

        if(videoIds.size()>0) {
            vodService.deleteBatch(videoIds);
        }

        wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        baseMapper.delete(wrapper);
    }

}
