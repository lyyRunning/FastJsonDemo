package com.function.luo.fastjsondemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luo on 2019/7/11.
 */

public class UserGroup implements Serializable{

    private String name;
    private List<User> users = new ArrayList<User>();

    public UserGroup() {
    }

    public UserGroup(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
