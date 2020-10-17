package com.diligentBees.System;

import com.diligentBees.domain.*;

import java.util.*;

/**
 * 动物管理系统主程序
 * 功能:
 *      1.展示已保存的所有动物的各类信息
 *      2.对集合中的动物进行增加、删除(全部删除or根据昵称删除)、修改、查询(根据昵称查询)等操作
 *      3.根据动物的年龄进行从小到大的排序
 */

public class Animal_System {
    //为不同类型的动物设计不同的容器
    private static List<Fish> fList=new ArrayList<>();
    private static List<Bird> bList=new ArrayList<>();
    private static List<Cat>  cList=new ArrayList<>();

    //将list容器存入Map集合中，是为了根据不同的动物名(key)获取对应的list(value)
    private static Map<String,List> animalMap=new HashMap<>();

    //与标准输入流关联的Scanner对象,用于输入
    private static Scanner scanner=new Scanner(System.in);

    //主函数，程序的入口
    public static void main(String[] args) {

        //首先将3个list添加到map中
        animalMap.put("fish",fList);
        animalMap.put("cat", cList);
        animalMap.put("bird",bList);


        while(true){
            System.out.println("----------------------欢迎来到动物管理系统----------------------");
            System.out.println("------   1. 所有动物                      2. 添加动物   ------");
            System.out.println("------   3. 删除动物                      4. 修改动物   ------");
            System.out.println("------   5. 查询动物                      6. 动物排序   ------");
            System.out.println("------   0. 退出系统                      version 1.0  ------");
            System.out.println("--------------------------------------------------------------");
            /*获取输入*/
            String next = scanner.next();
            switch(next){
                case "0":{
                    /*退出登录*/
                    exit_Switch();
                    break;
                }
                case "1":{
                    /*所有动物*/
                    print_Animal();
                    break;
                }
                case "2":{
                    /*添加动物*/
                    add_Switch();
                    break;
                }
                case "3":{
                    /*删除动物*/
                    delete_Switch();
                    break;
                }
                case "4":{
                    /*修改动物*/
                    update_Switch();
                    break;
                }
                case "5":{
                    /*查询动物*/
                    query_Switch();
                    break;
                }
                case "6":{
                    /*动物排序*/
                    sort_Switch();
                    break;
                }
                default:{
                    System.out.println("------>   您的输入非法");
                }
            }
        }
    }

    //退出系统---------------------------------------------------
    public static void exit_Switch(){
        /*再次确认用户是否要退出系统*/
        System.out.println("------>   你是否要退出系统(y / n)?");
        String confirm=scanner.next();
        if(confirm.equals("Y")||confirm.equals("y")){
            System.out.println("------>   退出系统！");
            System.exit(0);
        }
    }

    //所有动物---------------------------------------------------
    public static void print_Animal(){
        System.out.println("------>   目前 Fish 中有 "+fList.size()+" 个动物"+"\n");

        for (Fish fish : fList) {
            fish.printDescription();
        }

        System.out.println("------>   目前 Bird 中有 "+bList.size()+" 个动物"+"\n");

        for (Bird bird : bList) {
            bird.printDescription();
        }

        System.out.println("------>   目前 Cat  中有 "+cList.size()+" 个动物"+"\n");


        for (Cat cat : cList) {
            cat.printDescription();
        }
        System.out.println("\n");
    }
    //----------------------------------------------------------

    //添加动物---------------------------------------------------
    public static void add_Switch(){

        System.out.println("------>   你想要添加哪种动物?(fish or bird or cat)");
        String typeName = scanner.next();

        /*根据动物类型名获取对应的list容器*/
        List list = animalMap.get(typeName);

        if(list==null){
            System.out.println("------>   您的输入非法");
        }else {
            Animal animal = add_Animal(typeName);
            list.add(animal);
        }
        System.out.println("------>   您是否想继续添加动物(y or n)?");
        String next = scanner.next();
        if("Y".equals(next)||"y".equals(next)){
            //如果用户想继续添加动物就再次调用这个add_Switch()方法即可。
            add_Switch();
        }
    }

    public static Animal add_Animal(String typeName){
        System.out.println("------>   请输入昵称:");
        String name=scanner.next();
        System.out.println("------>   请输入种类:");
        String type=scanner.next();
        System.out.println("------>   请输入性别:");
        String sex=scanner.next();
        System.out.println("------>   请输入年龄:");
        String age_String=scanner.next();
        /*将String类型转成int类型*/
        int age = Integer.parseInt(age_String);

        if("bird".equals(typeName)){
            System.out.println("------>   是否可以飞行(true/false):");
            Boolean canFly=scanner.nextBoolean();
            return new Bird(name,type,sex,age,canFly);
        }else if("fish".equals(typeName)){
            System.out.println("------>   请输入外形:");
            String outline=scanner.next();
            return new Fish(name,type,sex,age,outline);
        }else if("cat".equals(typeName)){
            System.out.println("------>   请输入颜色:");
            String color=scanner.next();
            return new Cat(name,type,sex,age,color);
        }
        return null;
    }

    //删除动物---------------------------------------------------
    public static void delete_Switch(){
        System.out.println("------>   删除动物");
        System.out.println("------>   1. 删除所有动物");
        System.out.println("------>   2. 删除某个动物");
        String next2 = scanner.next();
        if("1".equals(next2)){
            /*删除前检验一下*/
            System.out.println("------>   你要删除所有的动物吗?(y or n)");
            String confirm=scanner.next();
            if(confirm.equals("Y")||confirm.equals("y")){
                delete_Animal();
                System.out.println("------>   删除成功");
            }
        }else if("2".equals(next2)){
            System.out.println("------>   请输入想要删除的动物的昵称:");
            String name = scanner.next();
            delete_Animal_ByName(name);
            System.out.println("------>   删除成功");
        }else{
            System.out.println("------>   您的输入非法");
        }
    }

    private static void delete_Animal_ByName(String name) {
        /*iterator()方法获取迭代器*/
        Iterator<Fish> iterator1 = fList.iterator();
        while (iterator1.hasNext()){
            Fish next = iterator1.next();
            if(name.equals(next.getName())){
                iterator1.remove();
            }
        }
        Iterator<Bird> iterator2 = bList.iterator();
        while (iterator2.hasNext()){
            Bird next = iterator2.next();
            if(name.equals(next.getName())){
                iterator2.remove();
            }
        }
        Iterator<Cat> iterator3 = cList.iterator();
        while (iterator3.hasNext()){
            Cat next = iterator3.next();
            if(name.equals(next.getName())){
                iterator3.remove();
            }
        }
    }

    private static void delete_Animal() {
        /*直接清空List容器*/
        fList.clear();
        cList.clear();
        bList.clear();
    }

    //修改动物---------------------------------------------------
    public static void update_Switch(){
        System.out.println("------>   修改动物");
        System.out.println("------>   请输入要修改的动物的昵称:");
        String name = scanner.next();
        update_Animal_ByName(name);
    }

    private static void update_Animal_ByName(String name) {

        int flag=0;
        for (Fish fish : fList) {
            if(name.equals(fish.getName())){
                fish.printDescription();
                update_Animal(fish);
                flag=1;
            }
        }

        for (Cat cat : cList) {
            if(name.equals(cat.getName())){
                cat.printDescription();
                update_Animal(cat);
                flag=1;
            }
        }

        for (Bird bird : bList) {
            if(name.equals(bird.getName())){
                bird.printDescription();
                update_Animal(bird);
                flag=1;
            }
        }

        if(flag==0){
            System.out.println("------>   名称为 "+name+" 的动物不存在!");
        }
    }

    private static void update_Animal(Animal animal){
        System.out.println("------>   请输入要修改的属性:");
        System.out.println("------>   0.昵称");
        System.out.println("------>   1.类型");
        System.out.println("------>   2.性别");
        System.out.println("------>   3.年龄");
        System.out.println("------>   4.外形/是否可以飞行/颜色");

        String choice = scanner.next();
        switch (choice){
            case "0":{
                System.out.println("------>   请输入昵称:");
                String update_name=scanner.next();
                animal.setName(update_name);
                break;
            }
            case "1":{
                System.out.println("------>   请输入种类:");
                String type=scanner.next();
                animal.setType(type);
                break;
            }
            case "2":{
                System.out.println("------>   请输入性别:");
                String sex=scanner.next();
                animal.setSex(sex);
                break;
            }
            case "3":{
                System.out.println("------>   请输入年龄:");
                String age_String=scanner.next();
                int age = Integer.parseInt(age_String);
                animal.setAge(age);
                break;
            }
            case "4":{
                if(animal instanceof Fish){
                    System.out.println("------>   请输入外形:");
                    String outline=scanner.next();
                    Fish fish= (Fish) animal;
                    fish.setOutline(outline);
                }
                if(animal instanceof Bird){
                    System.out.println("------>   是否可以飞行:");
                    boolean flag=scanner.nextBoolean();
                    Bird bird=(Bird) animal;
                    bird.setCanFly(flag);
                    break;
                }
                if(animal instanceof Cat){
                    System.out.println("------>   请输入颜色:");
                    String color=scanner.next();
                    Cat cat=(Cat) animal;
                    cat.setColor(color);
                    break;
                }
            }
            default:{
                System.out.println("------>   您的输入非法-");
            }
        }
        System.out.println("------>   你是否还要继续修改属性(y / n)?");
        String choice2 = scanner.next();
        if("y".equals(choice2)||"Y".equals(choice2)){
            update_Animal(animal);
        }
    }

    ///查询动物---------------------------------------------------
    public static void query_Switch(){
            System.out.println("------>   请输入要查询的动物的名字:");
            String name = scanner.next();
            for (Fish fish : fList) {
            if(name.equals(fish.getName())){
                fish.printDescription();
            }
        }

        for (Bird bird : bList) {
            if(name.equals(bird.getName())){
                bird.printDescription();
            }
        }

        for (Cat cat : cList) {
            if(name.equals(cat.getName())){
                cat.printDescription();
            }
        }
    }

    //动物排序---------------------------------------------------
    private static void sort_Switch() {
        System.out.println("------>   动物排序");
        sort_ByAge();
        print_Animal();
        System.out.println("------>   排序成功!!!");
    }

    public static void sort_ByAge(){
        /*利用Collections工具类的sort方法对list排序*/
        Collections.sort(fList);
        Collections.sort(bList);
        Collections.sort(cList);
    }
}
