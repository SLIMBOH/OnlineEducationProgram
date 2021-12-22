package com.suhao.purchase.feign;
import com.suhao.oledu.order.OrderClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-cli", fallback = HystrixSolution.class)
public interface ClientService {

    @GetMapping("/ucenter/client/getOrderMemberInfo/{memberId}")
    public OrderClient getOrderMemberInfo(@PathVariable("memberId") String memberId);

}
