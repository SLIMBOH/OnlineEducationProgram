package com.suhao.oledu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-purchase")
@Component
public interface OrderService {

    @GetMapping("/purchase/order/boughtOrNot")
    public boolean confirmPayment(@RequestParam("courseId") String courseId, @RequestParam("memberId") String memberId);

}
