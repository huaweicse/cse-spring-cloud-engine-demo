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
验证服务调用

```bash
curl http://127.0.0.1:8779/hello
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
验证服务调用

```bash
curl http://127.0.0.1:8778/hello
```
