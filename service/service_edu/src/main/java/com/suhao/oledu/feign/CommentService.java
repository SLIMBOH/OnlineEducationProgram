package com.suhao.oledu.feign;
import com.suhao.oledu.entity.vo.front.FrontClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-cli", fallback = HystrixSolution.class)
@Component
public interface CommentService {

    @PostMapping("/ucenter/client/getInfo/{id}")
    public FrontClient getInfo(@PathVariable String id);
}
