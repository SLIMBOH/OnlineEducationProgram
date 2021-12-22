package com.suhao.oledu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.EduCourseDescription;
import com.suhao.oledu.entity.vo.Course;
import com.suhao.oledu.entity.vo.CourseInfo;
import com.suhao.oledu.entity.vo.front.FrontCourse;
import com.suhao.oledu.entity.vo.front.FrontCourseDescription;
import com.suhao.oledu.handler.exception.MyException;
import com.suhao.oledu.mapper.EduCourseMapper;
import com.suhao.oledu.service.EduChapterService;
import com.suhao.oledu.service.EduCourseDescriptionService;
import com.suhao.oledu.service.EduCourseService;
import com.suhao.oledu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(Course course) {

        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(course, eduCourse);

        int insert = this.baseMapper.insert(eduCourse);

        if(insert == 0){
            throw new  MyException(20001, "Failed to add new course");
        }

        String id = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(course, eduCourseDescription);

        eduCourseDescription.setId(id);

        eduCourseDescriptionService.save(eduCourseDescription);

        return id;
    }

    @Override
    public Course getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        Course courseInfoVo = new Course();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(Course course) {

        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(course,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0) {
            throw new MyException(20001,"Failed to update course info");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(course.getId());
        description.setDescription(course.getDescription());
        eduCourseDescriptionService.updateById(description);
    }

    @Override
    public CourseInfo publishCourseInfo(String id) {
        CourseInfo publishCourseInfo = baseMapper.searchAllInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {

        eduVideoService.deleteVideoByCourseId(courseId);

        eduChapterService.deleteChapterByCourseId(courseId);

        eduCourseDescriptionService.removeById(courseId);

        int result = baseMapper.deleteById(courseId);
        if(result == 0) {
            throw new MyException(20001,"Failed to delete course");
        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, FrontCourse courseQuery) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(courseQuery.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseQuery.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseQuery.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseQuery.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseQuery.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageParam,wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public FrontCourseDescription getCourseDescription(String courseId) {
        return baseMapper.getCourseDescription(courseId);
    }
}
