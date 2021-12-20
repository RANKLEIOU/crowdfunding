package com.ajv.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private Integer id;

    private String authName;

    private String authTitle;

    private Integer categoryId;

}
