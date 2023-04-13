package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties {
    private final String name;
    private final String lastName;

    public MyBeanWithPropertiesImplement(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String function() {
        return name + " - " + lastName;
    }
}
