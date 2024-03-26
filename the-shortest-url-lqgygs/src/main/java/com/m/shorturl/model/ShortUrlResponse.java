package com.m.shorturl.model;

public class ShortUrlResponse {
    private String originalUrl;
    private String shortUrl;
    public ShortUrlResponse(String originalUrl, String shortUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public ShortUrlResponse(String originalUrl) {
        this.originalUrl = originalUrl;
        this.shortUrl = "BAD INPUT";
    }



    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "ShortUrlResponse{" +
                "originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
