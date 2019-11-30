package com.study.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.study.repository.entity.generate.User;
import com.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenglutao
 * @date 2019/11/30 14:02
 */
@Slf4j
@RestController
public class MyController {

    @Autowired
    UserService userService;

    @PostMapping("demo")
    public Object demo(@RequestParam String name){
        return userService.getName(name);
    }
}
