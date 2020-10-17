package com.diligentBees.domain;

/**
 * 猫类
 */
public class Cat extends Animal implements Comparable<Cat>{

    //猫的花色
    public String color;

    //实现compareTo方法,指定排序标准为年龄
    @Override
    public int compareTo(Cat cat) {
        return this.getAge()-cat.getAge();
    }

    //实现抽象方法
    @Override
    public void printDescription() {
        System.out.printf("Cat :昵称:%-10s     类型:%-10s     性别:%-10s     年龄:%-10d     颜色:%-10s",getName(),getType(),getSex(),getAge(),getColor());
        System.out.println();
    }

    public Cat() {
    }

    public Cat(String name,String type, String sex, int age, String color) {
        super(name,type, sex, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                "} " + super.toString();
    }
}
