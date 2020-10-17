package com.diligentBees.domain;

/**
 * 动物类
 */
public abstract class Animal {

    //定义所有动物公有的昵称、种类、性别、年龄等属性
    private String name;
    private String type;
    private String sex;
    private int age;

    /*
    * 抽象方法:输出动物描述
    * */
    public abstract void printDescription();

    public Animal() {
    }

    public Animal(String name,String type, String sex, int age) {
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
