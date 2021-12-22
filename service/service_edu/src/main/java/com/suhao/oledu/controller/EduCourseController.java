package com.suhao.oledu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhao.oledu.R;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.vo.Course;
import com.suhao.oledu.entity.vo.CourseInfo;
import com.suhao.oledu.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody Course course) {
        String id = eduCourseService.saveCourseInfo(course);
        return R.pass().data("courseId",id);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        Course course = eduCourseService.getCourseInfo(courseId);
        return R.pass().data("courseInfoVo",course);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody Course course) {
        eduCourseService.updateCourseInfo(course);
        return R.pass();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CourseInfo courseInfo = eduCourseService.publishCourseInfo(id);
        return R.pass().data("publishCourse",courseInfo);
    }

    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) Course course) {

        Page<EduCourse> coursePage = new Page<>(current,limit);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        String title = course.getTitle();
        String status = course.getStatus();

        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        eduCourseService.page(coursePage,wrapper);

        long total = coursePage.getTotal();
        List<EduCourse> records = coursePage.getRecords();
        return R.pass().data("total",total).data("rows",records);
    }

    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.pass();
    }

    @DeleteMapping("/delete/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return R.pass();
    }
}

