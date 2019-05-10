package com.penny.xlab.groovy;

import lombok.Data;

/**
 * @author 0-Vector
 * @date 2019/5/9 11:37
 */
@Data
public class Person {
    public String name;
    public String address;
    public Integer age;

    public Person(String name, String addr, Integer age){
        this.name = name;
        this.address = addr;
        this.age = age;
    }
}
