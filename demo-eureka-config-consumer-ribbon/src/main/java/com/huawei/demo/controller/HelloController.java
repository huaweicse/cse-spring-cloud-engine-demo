package com.huawei.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.demo.service.HelloService;

@RestController
public class HelloController {

  @Autowired
  private HelloService ribbonHelloService;

  @RequestMapping("/hello")
  public String hello() {
    return ribbonHelloService.hello();
  }
}
