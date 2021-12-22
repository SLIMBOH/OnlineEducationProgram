package com.suhao.cli.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.cli.entity.Client;
import com.suhao.cli.entity.register.ValueObject;
import com.suhao.cli.mapper.ClientMapper;
import com.suhao.cli.service.ClientService;
import com.suhao.oledu.JwtUtils;
import com.suhao.oledu.MD5;
import com.suhao.oledu.handler.exception.MyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(Client client) {
        //获取登录手机号和密码
        String mobile = client.getMobile();
        String password = client.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new MyException(20001,"登录失败");
        }

        QueryWrapper<Client> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Client mobileClient = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if(mobileClient == null) {//没有这个手机号
            throw new MyException(20001,"登录失败");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if(!MD5.encrypt(password).equals(mobileClient.getPassword())) {
            throw new MyException(20001,"登录失败");
        }

        //判断用户是否禁用
        if(mobileClient.getIsDisabled()) {
            throw new MyException(20001,"登录失败");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileClient.getId(), mobileClient.getNickname());
        return jwtToken;
    }

    //注册的方法
    @Override
    public void register(ValueObject valueObject) {

        String code = valueObject.getCode(); //验证码
        String mobile = valueObject.getMobile(); //手机号
        String nickname = valueObject.getNickname(); //昵称
        String password = valueObject.getPassword(); //密码

        //非空判断
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new MyException(20001,"insufficient info");
        }

        //判断验证码
        //获取redis验证码
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if(!code.equals(redisCode)) {
//            throw new MyException(20001,"Verify code is wrong");
//        }

        QueryWrapper<Client> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            throw new MyException(20001,"Duplicated phone number");
        }

        Client member = new Client();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    @Override
    public Client getOpenIdMember(String openid) {
        QueryWrapper<Client> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Client client = baseMapper.selectOne(wrapper);
        return client;
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }

}
