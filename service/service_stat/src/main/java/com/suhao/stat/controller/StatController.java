package com.suhao.stat.controller;

import com.suhao.oledu.R;
import com.suhao.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("/stat")
public class StatController {

    @Autowired
    private StatService statService;

    @PostMapping("/registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        statService.registerCount(day);
        return R.pass();
    }

    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statService.getShowData(type,begin,end);
        return R.pass().data(map);
    }
}

