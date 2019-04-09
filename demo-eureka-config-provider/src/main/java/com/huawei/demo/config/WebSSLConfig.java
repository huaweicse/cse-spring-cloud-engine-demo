package com.huawei.demo.config;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient.DiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@Configuration
@ConditionalOnProperty(name = "sslEnabled", havingValue = "true")
public class WebSSLConfig {
  @Bean
  public SSLContext sslContext() throws Exception {
    SSLContext context = SSLContext.getInstance("SSL");
    TrustManager trustManager = new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
          throws CertificateException {

      }

      @Override
      public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
          throws CertificateException {

      }

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };
    context.init(null, new TrustManager[]{trustManager}, null);
    return context;
  }

  // eureka 忽略 服务端 https 校验
  @Bean
  public DiscoveryClientOptionalArgs discoveryClientOptionalArgs(SSLContext sslContext) {
    DiscoveryClientOptionalArgs args = new DiscoveryClientOptionalArgs();
    EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
    builder.withClientName("HW-Eureka-PROVIDER")
        .withMaxTotalConnections(10)
        .withMaxConnectionsPerHost(10)
        .withCustomSSL(sslContext)
        .withHostnameVerifier((s, sslSession) -> true);
    args.setEurekaJerseyClient(builder.build());
    return args;
  }

  // config server 忽略服务端 https 校验
  @Bean
  public ConfigServicePropertySourceLocator configServicePropertySourceLocator(SSLContext sslContext, ConfigClientProperties configClientProperties) {
    ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(configClientProperties);
    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, (s, sslSession) -> true);
    CloseableHttpClient httpClient = HttpClients.custom()
        .setSSLSocketFactory(csf)
        .setSSLHostnameVerifier((s, sslSession) -> true)
        .build();
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(httpClient);

    RestTemplate restTemplate = new RestTemplate(requestFactory);
    configServicePropertySourceLocator.setRestTemplate(restTemplate);
    return configServicePropertySourceLocator;
  }
}
