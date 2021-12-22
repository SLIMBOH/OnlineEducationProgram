package com.suhao.oledu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class R {

    @ApiModelProperty(value = "Success or not")
    private Boolean success;

    @ApiModelProperty(value = "Result code")
    private Integer code;

    @ApiModelProperty(value = "Msg")
    private String message;

    @ApiModelProperty(value = "Data")
    private Map<String, Object> data = new HashMap<String, Object>();
    
    private R() {}
    
    public static R pass() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("Success");
        return r;
    }
    
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("Failed");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
