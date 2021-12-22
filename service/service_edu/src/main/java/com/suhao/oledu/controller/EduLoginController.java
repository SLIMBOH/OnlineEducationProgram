package com.suhao.oledu.controller;


import com.suhao.oledu.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@CrossOrigin
//@RestController
//@RequestMapping(value = "/edu/user")
@Deprecated
public class EduLoginController {

    @PostMapping(value = "login")
    public R login(){

        return R.pass().data("token", "admin");
    }

    @GetMapping(value = "info")
    public R info(){

        return R.pass().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
