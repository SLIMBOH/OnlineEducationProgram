package com.suhao.oledu.entity.vo.front;

import lombok.Data;

@Data
public class FrontCourse {

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;

    private String buyCountSort;

    private String gmtCreateSort;

    private String priceSort;
}
