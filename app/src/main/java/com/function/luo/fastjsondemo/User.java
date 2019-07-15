package com.function.luo.fastjsondemo;

import java.io.Serializable;

/**
 * Created by luo on 2019/7/11.
 */

public class User implements Serializable {

    private String name;
    private String phone;
    private int age;
    private String company;

    public User() {
    }

    public User(String name, String phone, int age, String company) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
