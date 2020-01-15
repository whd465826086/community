package com.example.community_test.Service;

import com.example.community_test.dto.PaginationDTO;
import com.example.community_test.dto.QuestionDTO;
import com.example.community_test.mapper.QuestionMapper;
import com.example.community_test.mapper.UserMapper;
import com.example.community_test.model.Question;
import com.example.community_test.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王海东1997 on 2020/1/14.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        Integer totalCount = questionMapper.count();

        Integer totalPage = 1;
        if(totalCount/size == 0){
            totalPage=totalCount/size;
        } else{
            totalPage = totalCount / size + 1;
        }
        if(page<1)
        {
            page = 1;
        }
        if(page>totalPage)
        {
            page = totalPage;
        }

        Integer offset = size * ( page - 1 );
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPaginition(totalPage,page,size);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {

        Integer totalCount = questionMapper.countByUserId(userId);

        Integer totalPage = 1;
        if(totalCount%size == 0 && totalCount>=size){
            totalPage=totalCount/size;
        } else{
            totalPage = totalCount / size + 1;
        }
        if(page<1)
        {
            page = 1;
        }
        if(page>totalPage)
        {
            page = totalPage;
        }
        Integer offset = size * ( page - 1 );
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPaginition(totalPage,page,size);
        return paginationDTO;


    }
}
