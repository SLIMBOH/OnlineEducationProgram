package com.suhao.oledu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.vo.CourseInfo;
import com.suhao.oledu.entity.vo.front.FrontCourseDescription;

public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CourseInfo searchAllInfo(String courseId);

    public FrontCourseDescription getCourseDescription(String courseId);

}
