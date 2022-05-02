package com.codetype.web.blog.service;

import com.codetype.web.blog.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
class UserServiceTest {

@Autowired
private UserService userService;
    @Test
    void getUsersName() {
        User admin = userService.getUsersName("codetype");
        System.out.println(admin);
    }
    @Test
    void test(){
//        ArrayList<String> list = new ArrayList<>();
//        list.add("hh");
//        list.add("Mejdell");
//        list.add("stuff");
//        list.add("Walk");
//        list.add("alkyds");
//        Random random = new Random();
//        int i = random.nextInt(list.size());
//        String s = list.get(i);
//        System.out.println(s);
//        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println();
    }

    @Test
    void port(){


    }

}