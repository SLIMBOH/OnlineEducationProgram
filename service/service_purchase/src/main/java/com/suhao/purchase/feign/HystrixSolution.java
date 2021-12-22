package com.suhao.purchase.feign;

import com.suhao.oledu.handler.exception.MyException;
import com.suhao.oledu.order.OrderClient;
import com.suhao.oledu.order.OrderCourse;
import org.springframework.stereotype.Component;


@Component
public class HystrixSolution implements ClientService, FrontCourseService{

    @Override
    public OrderClient getOrderMemberInfo(String memberId) {
        throw new MyException(20001, "Purchase module: feign method: getOrderMemberInfo error");
    }

    @Override
    public OrderCourse getFrontOrderCourseInfo(String courseId) {
        throw new MyException(20001, "Purchase module: feign method: getFrontOrderCourseInfo error");
    }
}
