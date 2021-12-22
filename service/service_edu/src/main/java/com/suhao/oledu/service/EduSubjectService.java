package com.suhao.oledu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.oledu.entity.EduSubject;
import com.suhao.oledu.entity.subject.FirstSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<FirstSubject> getAllSubjects();

}
