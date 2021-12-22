package com.suhao.oledu.controller;


import com.suhao.oledu.R;
import com.suhao.oledu.entity.subject.FirstSubject;
import com.suhao.oledu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/edu/subject")
//@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        subjectService.saveSubject(file,subjectService);
        return R.pass();
    }

    @GetMapping(value = "/getAllSubject")
    public R getAllSubject(){
        List<FirstSubject> allSubjects = subjectService.getAllSubjects();
        return R.pass().data("subjects", allSubjects);
    }

}

