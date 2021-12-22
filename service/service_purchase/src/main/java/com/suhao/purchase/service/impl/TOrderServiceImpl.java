package com.suhao.purchase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.oledu.order.OrderClient;
import com.suhao.oledu.order.OrderCourse;
import com.suhao.purchase.entity.TOrder;
import com.suhao.purchase.feign.ClientService;
import com.suhao.purchase.feign.FrontCourseService;
import com.suhao.purchase.mapper.TOrderMapper;
import com.suhao.purchase.service.TOrderService;
import com.suhao.purchase.utils.NoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private FrontCourseService frontCourseService;

    @Autowired
    private ClientService clientService;


    public String createOrder(String courseId, String id){

        OrderCourse orderCourse = frontCourseService.getFrontOrderCourseInfo(courseId);
        OrderClient orderClient = clientService.getOrderMemberInfo(id);


        TOrder order = new TOrder();
        order.setOrderNo(NoGenerator.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(orderCourse.getTitle());
        order.setCourseCover(orderCourse.getCover());
        order.setTeacherName(orderCourse.getTeacherName());
        order.setTotalFee(orderCourse.getPrice());
        order.setMemberId(id);
        order.setMobile(orderClient.getMobile());
        order.setNickname(orderClient.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }

}
