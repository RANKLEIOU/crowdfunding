package com.ajv.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String icon;

    private List<Menu> children = new ArrayList<>();

    private boolean open;

}
