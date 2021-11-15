package com.ajv.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Integer id;

    private String loginAcct;

    private String userPswd;

    private String userName;

    private String email;

    private String createTime;

}