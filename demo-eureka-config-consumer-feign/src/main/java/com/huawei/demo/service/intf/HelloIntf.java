package com.huawei.demo.service.intf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("demo-provider")
public interface HelloIntf {

  @RequestMapping(value = "/hello",method = RequestMethod.GET)
  String sayHello();

}
