package com.suhao.cli.controller;


import com.suhao.cli.entity.Client;
import com.suhao.cli.entity.FrontClient;
import com.suhao.cli.entity.register.ValueObject;
import com.suhao.cli.service.ClientService;
import com.suhao.oledu.JwtUtils;
import com.suhao.oledu.R;
import com.suhao.oledu.order.OrderClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin
@RequestMapping("/ucenter/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public R loginUser(@RequestBody Client client) {
        String token = clientService.login(client);
        return R.pass().data("token",token);
    }

    @PostMapping("/register")
    public R registerUser(@RequestBody ValueObject valueObject) {
        clientService.register(valueObject);
        return R.pass();
    }

    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Client member = clientService.getById(memberId);
        return R.pass().data("userInfo",member);
    }

    @PostMapping("/getInfo/{id}")
    public FrontClient getInfo(@PathVariable String id){
        Client client = clientService.getById(id);
        FrontClient frontClient = new FrontClient();
        BeanUtils.copyProperties(client,frontClient);
        return frontClient;
    }

    @GetMapping("/getOrderMemberInfo/{memberId}")
    public OrderClient getOrderMemberInfo(@PathVariable("memberId") String memberId){
        Client member = clientService.getById(memberId);
        OrderClient orderClient = new OrderClient();
        BeanUtils.copyProperties(member,orderClient);
        return orderClient;
    }

    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = clientService.countRegisterDay(day);
        return R.pass().data("countRegister",count);
    }
}

