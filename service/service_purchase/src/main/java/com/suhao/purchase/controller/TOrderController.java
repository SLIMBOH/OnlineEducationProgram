package com.suhao.purchase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suhao.oledu.JwtUtils;
import com.suhao.oledu.R;
import com.suhao.purchase.entity.TOrder;
import com.suhao.purchase.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin
@RequestMapping("/purchase/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    @PostMapping(value = "/createOrder/{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId, HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        if(id == ""){
            return R.error().code(28004);
        }
        System.out.println(courseId);
        System.out.println(id);
        String orderId = orderService.createOrder(courseId, id);
        return R.pass().data("orderId", orderId);
    }

    @GetMapping("/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.pass().data("item",order);
    }

//    @GetMapping(value = "/getAllOrder/{page}/{limit}")
//    public R getAllOrder(@PathVariable("limit") Long limit, @PathVariable("page") Long page, HttpServletRequest request){
//
//        return null;
//    }
//
//    @DeleteMapping(value = "/deleteOrder/{id}")
//    public R deleteOrder(@PathVariable("id") String orderId){
//
//        return null;
//    }

    @GetMapping("/boughtOrNot")
    public boolean confirmPayment(@RequestParam("courseId") String courseId, @RequestParam("memberId") String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        if(count>0) { //已经支付
            System.out.println("boughtOrNot over");
            return true;
        } else {
            return false;
        }
    }
}

