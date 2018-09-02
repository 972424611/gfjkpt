//package com.json.test;
//
//import com.cslg.gfjkpt.utils.HttpClientUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
//public class UserTest {
//
//    @Test
//    public void createUserTest() {
//        String url = "http://localhost:8080/user/register";
//        for(int i = 24; i <= 100; i++) {
//            Map<String, String> param = new HashMap<>();
//            param.put("username", "root" + i);
//            param.put("password", "123456");
//            param.put("comment", "长沙理工大学_" +  i);
//            String a = HttpClientUtils.doGet(url, param);
//            System.out.println(a);
//        }
//
//    }
//
//    @Test
//    public void userLoginTest() {
//        String url = "http://localhost:8080/user/login";
//        Map<String, String> param = new HashMap<>();
//        param.put("username", "admin");
//        param.put("password", "123dd456");
//        String a = HttpClientUtils.doGet(url, param);
//        System.out.println(a);
//    }
//}
