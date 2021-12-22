package com.suhao.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.purchase.entity.TOrder;

public interface TOrderService extends IService<TOrder> {

    public String createOrder(String courseId, String memberIdByJwtToken);

}
