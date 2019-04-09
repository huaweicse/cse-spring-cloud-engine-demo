package com.huawei.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.demo.service.HelloService;

@RestController
public class HelloController {

  @Autowired
  HelloService helloService;

  @GetMapping("/hello")
  public String sayHello() {
    return helloService.sayHello();
  }
}
