package com.huawei.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.demo.service.intf.HelloIntf;

@Service
public class HelloService {

  @Autowired
  HelloIntf helloIntf;

  public String sayHello() {
    String result = helloIntf.sayHello();
    return result == null ? "ERROR" : result;
  }
}
