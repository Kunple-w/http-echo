package com.goinghugh.utils;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * http请求工具类, 对于常见的http请求的封装
 *
 * @author wangyongxu
 * @version v1.0.0
 * @date 2019/7/31 21:38
 */
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    static {
        restTemplate = restTemplate();
    }

    public static HttpClientConnectionManager poolingConnectionManager() {
        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager();
        // 连接池最大连接数
        poolingConnectionManager.setMaxTotal(500);
        // 每个主机的并发
        poolingConnectionManager.setDefaultMaxPerRoute(100);

        return poolingConnectionManager;
    }

    public static HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // 设置HTTP连接管理器
        httpClientBuilder.setConnectionManager(poolingConnectionManager());
        return httpClientBuilder;
    }

    public static ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClientBuilder().build());
        // 连接超时，毫秒
        clientHttpRequestFactory.setConnectTimeout(6000);
        // 读写超时，毫秒
        clientHttpRequestFactory.setReadTimeout(6000);
        return clientHttpRequestFactory;
    }

    public static RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        return restTemplate;
    }


    public static <T> T getForObject(String url, HttpEntity<?> httpEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return exchangeForObject(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
    }

    public static <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.getForObject(url, responseType, uriVariables);
    }

    public static <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
        return restTemplate.getForObject(url, responseType, uriVariables);
    }

    public static <T> T getForObjectWithHeader(String url, Class<T> responseType, HttpHeaders headers, Map<String, ?> uriVariables) {
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return exchangeForObject(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
    }

    public static <T> T getForObjectWithHeader(String url, Class<T> responseType, HttpHeaders headers, Object... uriVariables) {
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        return exchangeForObject(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> getForEntity(String url, HttpEntity<?> httpEntity, Class<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.getForEntity(url, responseType, uriVariables);
    }


    public static <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.postForEntity(url, request, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) {
        return restTemplate.postForEntity(url, request, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
    }

    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
    }

    public static <T> T exchangeForObject(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        ResponseEntity<T> responseEntity = exchange(url, method, requestEntity, responseType, uriVariables);
        boolean successful = responseEntity.getStatusCode().is2xxSuccessful();
        if (successful) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("statusCode not 20x: " + responseEntity.getStatusCode());
        }
    }

    public static <T> T exchangeForObject(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        ResponseEntity<T> responseEntity = exchange(url, method, requestEntity, responseType, uriVariables);
        boolean successful = responseEntity.getStatusCode().is2xxSuccessful();
        if (successful) {
            return responseEntity.getBody();
        } else {
            throw new RuntimeException("statusCode not 20x: " + responseEntity.getStatusCode());
        }
    }

}