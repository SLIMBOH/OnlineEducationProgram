package com.suhao.oledu.feign;
import com.suhao.oledu.R;
import com.suhao.oledu.entity.vo.front.FrontClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HystrixSolution implements VodService, CommentService{

    @Override
    public R removeVideo(String id) {
        return R.error().message("removeVideo error");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("deleteBatch error");
    }

    @Override
    public FrontClient getInfo(String id) {
        return null;
    }

//    @Override
//    public boolean boughtOrNot(String courseId, String memberId) {
//        System.out.println("fallback");
//        return false;
//    }
}
