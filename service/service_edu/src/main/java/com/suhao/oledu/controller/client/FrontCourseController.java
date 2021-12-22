package com.suhao.oledu.controller.client;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhao.oledu.JwtUtils;
import com.suhao.oledu.R;
import com.suhao.oledu.entity.EduCourse;
import com.suhao.oledu.entity.chapter.Chapter;
import com.suhao.oledu.entity.vo.front.FrontCourse;
import com.suhao.oledu.entity.vo.front.FrontCourseDescription;
import com.suhao.oledu.feign.OrderService;
import com.suhao.oledu.order.OrderCourse;
import com.suhao.oledu.service.EduChapterService;
import com.suhao.oledu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping(value = "/edu/front/course")
public class FrontCourseController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) FrontCourse courseQuery) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse, courseQuery);
        return R.pass().data(map);

    }

    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {

        FrontCourseDescription frontCourseDescription = courseService.getCourseDescription(courseId);

        List<Chapter> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        String memberId = JwtUtils.getMemberIdByJwtToken(request);
//        String memberId = "1471804656538705921";
//
//        System.out.println(memberId);
//        System.out.println(courseId);

        boolean bought = orderService.confirmPayment(courseId, memberId);

        return R.pass().data("frontCourseDescription", frontCourseDescription).data("chapterVideoList", chapterVideoList).data("bought", bought);
    }

    @GetMapping("/getFrontOrderCourseInfo/{courseId}")
    public OrderCourse getFrontOrderCourseInfo(@PathVariable String courseId){
        FrontCourseDescription courseInfo = courseService.getCourseDescription(courseId);
        OrderCourse orderCourse = new OrderCourse();
        BeanUtils.copyProperties(courseInfo, orderCourse);
        return orderCourse;
    }
}
