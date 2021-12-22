package com.suhao.oledu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * for edit data callback
 */
@Data
public class Course {

    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String status;

    private String cover;

    private String description;

}
