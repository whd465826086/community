package com.example.community_test.dto;

import com.example.community_test.model.User;
import lombok.Data;

/**
 * Created by 王海东1997 on 2020/1/14.
 */
@Data
public class QuestionDTO {
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

    private User user;
}
