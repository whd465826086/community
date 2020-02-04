package com.example.community_test.exception;

/**
 * Created by 王海东1997 on 2020/2/4.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("无该问题或已被删除");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
