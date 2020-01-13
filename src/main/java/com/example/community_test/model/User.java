package com.example.community_test.model;

import lombok.Data;

/**
 * Created by 王海东1997 on 2020/1/6.
 */
@Data
public class User {
  private Integer id;
  private String accountId;
  private String name;
  private String token;
  private Long gmtCreate;
  private Long gmtModified;
  private String avatarUrl;


}
