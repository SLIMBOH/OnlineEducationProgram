package com.suhao.cms.controller;

import com.suhao.cms.entity.CrmBanner;
import com.suhao.cms.service.CrmBannerService;
import com.suhao.oledu.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/client/banner")
//@CrossOrigin
public class ClientBannerController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.pass().data("list",list);
    }
}
