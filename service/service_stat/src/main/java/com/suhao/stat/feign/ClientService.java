package com.suhao.stat.feign;

import com.suhao.oledu.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-cli")
public interface ClientService {
    @GetMapping("/ucenter/client/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
