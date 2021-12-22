package com.suhao.oledu.service;

import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.EduTeacher;

import java.util.List;

public interface FrontPageService {

    List<EduTeacher> getPopularTeacher();

    List<EduCourse> getPopularCourse();

}
