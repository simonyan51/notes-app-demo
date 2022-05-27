package io.gnelsimonyan.notes.externalapis.common;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class HttpClientExecutor<T> {
    private String url;

    private HttpMethod method;

    private Object body;

    private HttpHeaders headers;

    private Class<T> classType;

    public static <T> HttpClientExecutor<T> create() {
        return new HttpClientExecutor<>();
    }

    private HttpClientExecutor() {
        headers = new HttpHeaders();
    }

    public HttpClientExecutor<T> setUrl(String url) {
        this.url = url;

        return this;
    }

    public HttpClientExecutor<T> setMethod(HttpMethod method) {
        this.method = method;

        return this;
    }

    public HttpClientExecutor<T> setBody(Object body) {
        this.body = body;

        return this;
    }

    public HttpClientExecutor<T> setClassType(Class<T> classType) {
        this.classType = classType;

        return this;
    }

    public HttpClientExecutor<T> appendHeader(String key, String value) {
        headers.add(key, value);

        return this;
    }

    public HttpClientExecutor<T> acceptJSON() {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return this;
    }

    public T execute() {
        ResponseEntity<T> response = new RestTemplate()
                .exchange(url, method, new HttpEntity<>(headers), classType);

        return response.getBody();
    }
}
