package com.suhao.oledu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.oledu.entity.EduTeacher;

import java.util.Map;


public interface EduTeacherService extends IService<EduTeacher> {

    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageParam);

}
