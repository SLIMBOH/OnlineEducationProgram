package com.suhao.oledu.feign;
import com.suhao.oledu.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod", fallback = HystrixSolution.class)
@Component
public interface VodService {

    @DeleteMapping("/vod/video/removeVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/vod/video/batchDelete")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
