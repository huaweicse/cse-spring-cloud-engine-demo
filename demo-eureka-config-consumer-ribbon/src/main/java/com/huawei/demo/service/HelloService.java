package com.huawei.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

  @Autowired
  RestTemplate restTemplate;

  public String hello() {
    return restTemplate.getForObject("http://DEMO-PROVIDER/hello", String.class);
  }
}
