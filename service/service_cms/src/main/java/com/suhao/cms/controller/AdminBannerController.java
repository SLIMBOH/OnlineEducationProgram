package com.suhao.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhao.cms.entity.CrmBanner;
import com.suhao.cms.service.CrmBannerService;
import com.suhao.oledu.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/admin/banner")
//@CrossOrigin
public class AdminBannerController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
//        System.out.println(pageBanner.getTotal());
        int total = bannerService.count(null);
        return R.pass().data("items",pageBanner.getRecords()).data("total", total);
    }

    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.pass();
    }

    @GetMapping("/get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.pass().data("item", banner);
    }

    @PostMapping("/update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.pass();
    }

    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.pass();
    }

}

