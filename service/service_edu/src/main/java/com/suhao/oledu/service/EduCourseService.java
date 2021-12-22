package com.suhao.oledu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.vo.Course;
import com.suhao.oledu.entity.vo.CourseInfo;
import com.suhao.oledu.entity.vo.front.FrontCourse;
import com.suhao.oledu.entity.vo.front.FrontCourseDescription;

import java.util.Map;

public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(Course courseQuery);

    Course getCourseInfo(String courseId);

    void updateCourseInfo(Course course);

    CourseInfo publishCourseInfo(String id);

    void removeCourse(String courseId);

    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, FrontCourse courseQuery);

    public FrontCourseDescription getCourseDescription(String courseId);
}
