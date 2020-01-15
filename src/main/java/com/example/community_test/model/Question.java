package com.example.community_test.model;

import lombok.Data;

/**
 * Created by 王海东1997 on 2020/1/13.
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}