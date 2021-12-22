package com.suhao.oledu.controller.client;

import com.suhao.oledu.R;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.EduTeacher;
import com.suhao.oledu.service.FrontPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/edu/client")
//@CrossOrigin
public class FrontPageController {

    @Autowired
    private FrontPageService frontPageService;

    @GetMapping("/popular")
    public R popular() {

        List<EduCourse> eduList = frontPageService.getPopularCourse();
        List<EduTeacher> teacherList = frontPageService.getPopularTeacher();

        return R.pass().data("eduList",eduList).data("teacherList",teacherList);
    }

}
