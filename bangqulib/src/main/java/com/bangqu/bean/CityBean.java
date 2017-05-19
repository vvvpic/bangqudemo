package com.bangqu.bean;

import java.io.Serializable;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class CityBean implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
