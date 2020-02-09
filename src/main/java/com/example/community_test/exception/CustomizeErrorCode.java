package com.example.community_test.exception;

/**
 * Created by 王海东1997 on 2020/2/4.
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"无该问题或已被删除"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论"),
    NO_LOGIN(2003,"未登录，请登录后重试"),
    SYSTEM_ERROR(2004,"服务器异常，请重试"),
    COMMENT_TYPE_ERROR(2005,"评论类型错误"),
    COMMENT_NOT_FOUND(2006,"该评论不存在")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;

    }

    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
