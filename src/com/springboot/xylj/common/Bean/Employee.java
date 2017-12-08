package com.springboot.xylj.common.Bean;

/**
 * 员工bean
 * Created by shihao 2017/9/22 18:30
 */
public class Employee {

    private String name;

    public Employee(String n) {
        name = n;
    }

    @Override
    public boolean equals(Object t) {
        return t instanceof Employee && name.equals(((Employee) t).name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
