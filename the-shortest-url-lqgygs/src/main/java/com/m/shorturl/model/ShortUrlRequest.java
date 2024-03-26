package com.m.shorturl.model;

public class ShortUrlRequest {
    private String originalUrl;

    public ShortUrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }
    public ShortUrlRequest() {}

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlRequest{" +
                "originalUrl='" + originalUrl + '\'' +
                '}';
    }
}
