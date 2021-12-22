package com.suhao.stat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.stat.entity.Stat;

import java.util.Map;

public interface StatService extends IService<Stat> {

    void registerCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
