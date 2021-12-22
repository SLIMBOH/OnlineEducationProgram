package com.suhao.oledu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suhao.oledu.JwtUtils;
import com.suhao.oledu.R;
import com.suhao.oledu.entity.EduComment;
import com.suhao.oledu.entity.vo.front.FrontClient;
import com.suhao.oledu.feign.CommentService;
import com.suhao.oledu.service.EduCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin
@RequestMapping("/edu/comment")
public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/getCommentList/{page}/{limit}")
    public R getCommentList(@PathVariable("page") Long page, @PathVariable("limit") Long limit, String courseId){

        Page<EduComment> commentPage = new Page<>(page, limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        eduCommentService.page(commentPage, queryWrapper);

        List<EduComment> commentList = commentPage.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", commentPage.getCurrent());
        map.put("pages", commentPage.getPages());
        map.put("size", commentPage.getSize());
        map.put("total", commentPage.getTotal());
        map.put("hasNext", commentPage.hasNext());
        map.put("hasPrevious", commentPage.hasPrevious());
        return R.pass().data(map);
    }

    @PostMapping(value = "/addComment")
    public R addComment(@RequestBody EduComment comment, HttpServletRequest request){

        String id = null;
        try {
            id = JwtUtils.getMemberIdByJwtToken(request);
        } catch (Exception e) {
            return R.error().code(28004).message("Please sign in first");
        }

        if(StringUtils.isEmpty(id)) {
            return R.error().code(28004).message("Please sign in first");
        }
        comment.setMemberId(id);
        FrontClient frontClient = commentService.getInfo(id);
        comment.setNickname(frontClient.getNickname());
        comment.setAvatar(frontClient.getAvatar());
        eduCommentService.save(comment);
        return R.pass();
    }
}

