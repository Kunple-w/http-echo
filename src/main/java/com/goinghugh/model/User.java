package com.goinghugh.model;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>用户</p>
 *
 * @author wangyongxu
 * @date 2019/6/19 18:43
 */
public class User implements Serializable {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("age=" + age)
                .toString();
    }
}
