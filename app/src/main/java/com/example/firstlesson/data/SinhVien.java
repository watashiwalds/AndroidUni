package com.example.firstlesson.data;

import java.io.Serializable;

public class SinhVien implements Serializable {
    public String name;
    public Integer yob;

    public SinhVien(String name, Integer yob) {
        this.name = name;
        this.yob = yob;
    }

    public void setHoten(String value) {
        name = value;
    }
    public void setNamsinh(Integer value) {
        yob = value;
    }
    public String getHoten() {
        return name;
    }
    public Integer getNamsinh() {
        return yob;
    }
}
