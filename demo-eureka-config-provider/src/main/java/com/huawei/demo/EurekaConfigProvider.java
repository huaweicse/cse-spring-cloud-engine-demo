package com.huawei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaConfigProvider {
  public static void main(String[] args) {
    SpringApplication.run(EurekaConfigProvider.class, args);
  }
}
