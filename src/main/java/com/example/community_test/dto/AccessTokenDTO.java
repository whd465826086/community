package com.example.community_test.dto;

import lombok.Data;

/**
 * Created by 王海东1997 on 2019/12/25.
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
