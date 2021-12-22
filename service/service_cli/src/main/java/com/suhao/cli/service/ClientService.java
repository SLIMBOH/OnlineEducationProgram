package com.suhao.cli.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.cli.entity.Client;
import com.suhao.cli.entity.register.ValueObject;

public interface ClientService extends IService<Client> {

    String login(Client Client);

    public void register(ValueObject valueObject);

    public Client getOpenIdMember(String openid);

    public Integer countRegisterDay(String day);
}
