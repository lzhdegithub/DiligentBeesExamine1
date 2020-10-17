package com.diligentBees.domain;

/**
 * 鱼类：继承于Animal类 实现Comparable接口
 */
public class Fish extends Animal implements Comparable<Fish> {

    //鱼独有的特点:鱼的体形。如流线型状，剪刀状等等
    private String outline;

    //实现compareTo方法,指定排序标准为年龄
    @Override
    public int compareTo(Fish fish) {
        return this.getAge()-fish.getAge();
    }

    //实现抽象方法
    @Override
    public void printDescription() {
        System.out.printf("Fish:昵称:%-10s     类型:%-10s     性别:%-10s     年龄:%-10d     外形:%-10s",getName(),getType(),getSex(),getAge(),getOutline());
        System.out.println();
    }

    public Fish(){

    }

    public Fish(String outline) {
        this.outline = outline;
    }

    public Fish(String name,String type,String sex,int age,String outLine) {
        super(name,type,sex,age);
        this.outline=outLine;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    @Override
    public String toString() {
        return "Fish{"+
                "type="+getType()+
                "sex="+getSex()+
                "age="+getAge()+
                "Outline="+outline+
                "}";
    }

}
