package com.suhao.oledu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.EduTeacher;
import com.suhao.oledu.service.EduCourseService;
import com.suhao.oledu.service.EduTeacherService;
import com.suhao.oledu.service.FrontPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrontPageServiceImpl implements FrontPageService {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @Override
    @Cacheable(value = "teacher", key = "'selectIndexList'")
    public List<EduTeacher> getPopularTeacher() {
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        return teacherService.list(wrapperTeacher);
    }

    @Override
    @Cacheable(value = "course", key = "'selectIndexList'")
    public List<EduCourse> getPopularCourse() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        return courseService.list(wrapper);
    }
}
