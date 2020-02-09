package com.example.community_test.mapper;

import com.example.community_test.model.Question;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}