package com.tl.demo.springboot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("test.config")
public class TestConfig {

    private String url;
    private int timeout;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return String.format("{%s=%s, %s=%s}", "url", url, "timeout", timeout);
    }
}
