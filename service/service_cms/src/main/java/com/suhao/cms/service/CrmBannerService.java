package com.suhao.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.cms.entity.CrmBanner;

import java.util.List;

public interface CrmBannerService extends IService<CrmBanner> {

    public List<CrmBanner> selectAllBanner();

}
