package com.example.community_test.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王海东1997 on 2020/1/14.
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showProvious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPaginition(Integer totalPage, Integer page, Integer size) {

        if(page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.totalPage=totalPage;
        this.page=page;
        pages.add(page);
        for (int i = 1;i <= 3;i++)
        {
            if(page-i>0)
            {
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        if(page == 1)
        {
            showProvious = false;
            showFirstPage = false;
        }else {
            showProvious = true;
            if(pages.contains(1)) {
                showFirstPage = false;
            }else {
                showFirstPage = true;
            }
        }

        if(page == totalPage)
        {
            showNext = false;
            showEndPage = false;
        }else {
            showNext = true;
            if(pages.contains(totalPage)) {
                showEndPage = false;
            }else {
                showEndPage = true;
            }
        }
    }
}
