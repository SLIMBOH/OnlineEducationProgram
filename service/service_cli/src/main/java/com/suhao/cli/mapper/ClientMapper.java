package com.suhao.cli.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suhao.cli.entity.Client;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper extends BaseMapper<Client> {
    public Integer countRegisterDay(String day);
}
