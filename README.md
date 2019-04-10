# cse-spring-cloud-engine-demo

# 启动 provider

**首先配置好服务发现地址**

* 进入 **demo-eureka-config-provider** 模块

```bash
# 接入 https SpringCloud 引擎
mvn clean install
java -jar -DsslEnabled=true xxx.jar
# 接入 http SpringCloud 引擎
mvn clean install
java -jar xxx.jar
```
# 验证 ribbon 调用

* 进入 **demo-eureka-config-consumer-ribbon** 模块

```bash
# 接入 https SpringCloud 引擎
mvn clean install
java -jar -DsslEnabled=true xxx.jar
# 接入 http SpringCloud 引擎
mvn clean install
java -jar xxx.jar
```

* 验证服务调用

```bash
curl http://127.0.0.1:8779/hello
# 如果没有连接 config server 或者 config server 没有配置 hello.huawei
# 则返回应该是 PROVIDER-DEFAULT-VALUE, 表示 consumer 成功调用 provider
# 如果连接了 config server 而且已经配置 hello.huawei=xxx
# 则返回应该是 xxx, 表示 consumer 成功调用provider, 并且 provider 成功从config server 拉取配置
```
# 验证 feign 调用

* 进入 **demo-eureka-config-consumer-feign** 模块

```bash
# 接入 https SpringCloud 引擎
mvn clean install
java -jar -DsslEnabled=true xxx.jar
# 接入 http SpringCloud 引擎
mvn clean install
java -jar xxx.jar
```

* 验证服务调用

```bash
curl http://127.0.0.1:8778/hello
# 如果没有连接 config server 或者 config server 没有配置 hello.huawei
# 则返回应该是 PROVIDER-DEFAULT-VALUE, 表示 consumer 成功调用 provider
# 如果连接了 config server 而且已经配置 hello.huawei=xxx
# 则返回应该是 xxx, 表示 consumer 成功调用provider, 并且 provider 成功从config server 拉取配置
```
