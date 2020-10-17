package com.diligentBees.domain;

/**
 * 鸟类
 */
public class Bird extends Animal implements Comparable<Bird> {

    //鸟类是否能飞行
    private boolean canFly;

    //实现compareTo方法,指定排序标准为年龄
    @Override
    public int compareTo(Bird bird) {
        return this.getAge()-bird.getAge();
    }

    //实现抽象方法
    @Override
    public void printDescription() {
        System.out.printf("Bird:昵称:%-10s     类型:%-10s     性别:%-10s     年龄:%-10d     是否可以飞行:%-10s",getName(),getType(),getSex(),getAge(),canFly);
        System.out.println();
    }

    public Bird() {
    }

    public Bird(String name,String type, String sex, int age, boolean canFly) {
        super(name,type, sex, age);
        this.canFly = canFly;
    }

    public boolean getCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "canFly=" + canFly +
                "} " + super.toString();
    }
}
