package com.njupt.sniper.app.model.entity;

import java.io.Serializable;

/**
 * author：Zsl
 * date：2016/5/6
 */
public class BaseIdName implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
