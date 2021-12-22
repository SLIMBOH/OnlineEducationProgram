package com.suhao.oledu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.oledu.entity.EduVideo;

public interface EduVideoService extends IService<EduVideo> {

    public void deleteVideoByCourseId(String courseId);

}
