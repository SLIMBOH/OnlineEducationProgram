package com.suhao.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.purchase.entity.TPayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author suhao
 * @since 2021-12-19
 */
public interface TPayLogService extends IService<TPayLog> {

    //生成微信支付二维码接口
    Map createNative(String orderNo);

    //查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //添加支付记录和更新订单状态
    void updateOrdersStatus(Map<String, String> map);
}
