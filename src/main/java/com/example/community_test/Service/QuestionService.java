package com.example.community_test.Service;

import com.example.community_test.dto.PaginationDTO;
import com.example.community_test.dto.QuestionDTO;
import com.example.community_test.mapper.QuestionMapper;
import com.example.community_test.mapper.UserMapper;
import com.example.community_test.model.Question;
import com.example.community_test.model.QuestionExample;
import com.example.community_test.model.User;
import org.apache.ibatis.session.RowBounds;
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

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());

        Integer totalPage = 1;
        if(totalCount%size == 0){
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
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);


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
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPaginition(totalPage,page,size);
        return paginationDTO;


    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            Question updateQusetion = new Question();
            updateQusetion.setGmtModified(System.currentTimeMillis());
            updateQusetion.setTag(question.getTag());
            updateQusetion.setDescription(question.getDescription());
            updateQusetion.setTitle(question.getTitle());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(updateQusetion, example);
        }
    }
}
