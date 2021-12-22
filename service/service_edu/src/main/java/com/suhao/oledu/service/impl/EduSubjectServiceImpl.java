package com.suhao.oledu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suhao.oledu.entity.EduSubject;
import com.suhao.oledu.entity.excel.SubjectData;
import com.suhao.oledu.entity.subject.FirstSubject;
import com.suhao.oledu.entity.subject.SecondSubject;
import com.suhao.oledu.listener.SubjectExcelListener;
import com.suhao.oledu.mapper.EduSubjectMapper;
import com.suhao.oledu.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<FirstSubject> getAllSubjects() {

        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> firstSubjectList = baseMapper.selectList(wrapper1);

        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> secondSubjectList = baseMapper.selectList(wrapper2);

        List<FirstSubject> allSubjectList = new ArrayList<>();

        for (int i = 0; i < firstSubjectList.size(); i++) {
            EduSubject eduSubject = firstSubjectList.get(i);
            FirstSubject firstSubject = new FirstSubject();
            BeanUtils.copyProperties(eduSubject,firstSubject);
            allSubjectList.add(firstSubject);

            List<SecondSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < secondSubjectList.size(); j++) {
                EduSubject eduSubject2 = secondSubjectList.get(j);
                if(eduSubject2.getParentId().equals(eduSubject.getId())) {
                    SecondSubject secondSubject = new SecondSubject();
                    BeanUtils.copyProperties(eduSubject2,secondSubject);
                    twoFinalSubjectList.add(secondSubject);
                }
            }
            firstSubject.setSecondSubjects(twoFinalSubjectList);
        }

        return allSubjectList;
    }
}
