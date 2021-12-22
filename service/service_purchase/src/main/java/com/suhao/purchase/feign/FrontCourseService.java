package com.suhao.purchase.feign;


import com.suhao.oledu.order.OrderCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-edu", fallback = HystrixSolution.class)
@Component
public interface FrontCourseService {

    @GetMapping("/edu/front/course/getFrontOrderCourseInfo/{courseId}")
    public OrderCourse getFrontOrderCourseInfo(@PathVariable String courseId);

}
