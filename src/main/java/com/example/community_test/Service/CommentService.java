package com.example.community_test.Service;

import com.example.community_test.enums.CommentTypeEnum;
import com.example.community_test.exception.CustomizeErrorCode;
import com.example.community_test.exception.CustomizeException;
import com.example.community_test.mapper.CommentMapper;
import com.example.community_test.mapper.QuestionExtMapper;
import com.example.community_test.mapper.QuestionMapper;
import com.example.community_test.model.Comment;
import com.example.community_test.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 王海东1997 on 2020/2/7.
 */
@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionExtMapper questionExtMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null ||comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType()))
        {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType())
        {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getId());
            if (dbComment == null)
            {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }
        if (comment.getType() ==  CommentTypeEnum.QUESTION.getType())
        {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question == null)
            {
                throw new CustomizeException(CustomizeErrorCode. QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
