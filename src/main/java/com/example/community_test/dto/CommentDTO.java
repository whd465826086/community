package com.example.community_test.dto;

import lombok.Data;

/**
 * Created by 王海东1997 on 2020/2/6.
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
