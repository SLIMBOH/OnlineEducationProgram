package com.suhao.stat.utils;

import com.suhao.stat.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Timing {

    @Autowired
    private StatService statService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        statService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
