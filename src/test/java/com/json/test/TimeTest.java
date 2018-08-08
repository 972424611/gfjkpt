//package com.json.test;
//
//import org.junit.Test;
//
//import java.util.*;
//
//public class TimeTest {
//
//    @Test
//    public void test() {
//        System.out.println(new Date());
//        System.out.println(new Date().getDay());
//        System.out.println(new Date().getMonth());
//        System.out.println(new Date().getDate());
//        System.out.println(new Date().getHours());
//        System.out.println(System.currentTimeMillis());
//    }
//
//    @Test
//    public void test2() {
//        long start = System.currentTimeMillis();
//        StringBuilder stringBuilder = new StringBuilder();
//        for(int i = 0; i < 1000000; i++) {
//            stringBuilder.append("aaa" + i);
//        }
//        long end = System.currentTimeMillis();
//        //stringBuilder.toString();
//        System.out.println(end - start);
//
//        start = System.currentTimeMillis();
//        StringBuffer stringBuffer = new StringBuffer();
//        for(int i = 0; i < 1000000; i++) {
//            stringBuffer.append("aaa" + i);
//        }
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//        start = System.currentTimeMillis();
//        String[] strs2 = new String[1000000];
//        for(int i = 0; i < 1000000; i++) {
//            strs2[i] = "aaa" + i;
//        }
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//        start = System.currentTimeMillis();
//        List<String> stringList = new ArrayList<>();
//        for(int i = 0; i < 1000000; i++) {
//            stringList.add("aaa" + i);
//        }
//        String[] strs = (String[]) stringList.
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
//
//        start = System.currentTimeMillis();
//        Set<String> set = new HashSet<>();
//        for(int i = 0; i < 1000000; i++) {
//            set.add("aaa" + i);
//        }
//        end = System.currentTimeMillis();
//        System.out.println(end - start);
//    }
//}
