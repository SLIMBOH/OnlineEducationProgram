package com.suhao.oledu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suhao.oledu.entity.EduChapter;
import com.suhao.oledu.entity.chapter.Chapter;

import java.util.List;


public interface EduChapterService extends IService<EduChapter> {


    List<Chapter> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    public void deleteChapterByCourseId(String courseId);

}
