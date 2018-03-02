//package com.json.test;
//
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//public class CollectionTest {
//
//    @Test
//    public void arrayModifyTest() {
//        Collection userList = new ArrayList();
//        userList.add(new User("张三", 28));
//        userList.add(new User("李四", 25));
//        userList.add(new User("王五", 31));
//        Iterator iterator = userList.iterator();
//        while(iterator.hasNext()) {
//            User user = (User) iterator.next();
//            if("李四".equals(user.getName())) {
//                userList.remove(user);
//            }else {
//                System.out.println(user);
//            }
//        }
//    }
//}
//
//class User {
//    private String name;
//    private int age;
//
//    public User(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}
