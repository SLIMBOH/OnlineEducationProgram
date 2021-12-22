package com.suhao.oledu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suhao.oledu.entity.EduSubject;
import com.suhao.oledu.entity.excel.SubjectData;
import com.suhao.oledu.handler.exception.MyException;
import com.suhao.oledu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData == null) {
            throw new MyException(20001,"File is empty");
        }

        EduSubject existFirstSubject = this.existFirstSubject(subjectService, subjectData.getFirstSubjectName());
        if(existFirstSubject == null) {
            existFirstSubject = new EduSubject();
            existFirstSubject.setParentId("0");
            existFirstSubject.setTitle(subjectData.getFirstSubjectName());
            subjectService.save(existFirstSubject);
        }

        String pid = existFirstSubject.getId();

        EduSubject existSecondSubject = this.existSecondSubject(subjectService, subjectData.getSecondSubjectName(), pid);
        if(existSecondSubject == null) {
            existSecondSubject = new EduSubject();
            existSecondSubject.setParentId(pid);
            existSecondSubject.setTitle(subjectData.getSecondSubjectName());
            subjectService.save(existSecondSubject);
        }
    }

    private EduSubject existFirstSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject firstSubject = subjectService.getOne(wrapper);
        return firstSubject;
    }

    private EduSubject existSecondSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject secondSubject = subjectService.getOne(wrapper);
        return secondSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
