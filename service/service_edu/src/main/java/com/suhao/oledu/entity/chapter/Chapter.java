package com.suhao.oledu.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chapter {

    private String id;

    private String title;

    private List<Video> videos = new ArrayList<>();
}
