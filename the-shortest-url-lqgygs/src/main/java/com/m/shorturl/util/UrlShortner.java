package com.m.shorturl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UrlShortner {
    //List to store the shortURL keys
    private static final List<String> shortUrlKeyList = new ArrayList<>();
    //Map to store the shortURL key and actual LongURL
    private static final Map<String,String> urlKeyMap = new HashMap<>();
    private final Random rand = new Random();
    private final String short3MURL = "http://short3murl.com/";
    @Value("${short.url.max.length}")
    private String shortUrlLen;

    public void setShortUrlLen(String shortUrlLen) {
        this.shortUrlLen = shortUrlLen;
    }

    public void setShortUrlKeyList(List<String>  shortUrlKeyList) {
        UrlShortner.shortUrlKeyList.addAll(shortUrlKeyList);
    }

    public void setUrlKeyMap(Map<String,String> urlKeyMap) {
        UrlShortner.urlKeyMap.putAll(urlKeyMap);
    }

    /** Encodes a URL to a shortened URL.
     * If short URL already exists then it regenerates the shortURL
     * @returns shortURL
     */
    public String encode(String longUrl) {
        //Base64.getUrlEncoder().encodeToString(longUrl.getBytes());
        int urlLength = Integer.parseInt(shortUrlLen);
        String newShortURL;
        char [] shortURLKey = new char[urlLength];
        String base64Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/";

        for(int i = 0; i < urlLength; i++ )
            shortURLKey[i] = base64Chars.charAt(rand.nextInt(base64Chars.length()));

        if(!shortUrlKeyList.contains(String.copyValueOf(shortURLKey))){
            newShortURL = short3MURL + String.copyValueOf(shortURLKey);
            shortUrlKeyList.add(String.copyValueOf(shortURLKey));
            urlKeyMap.put(String.copyValueOf(shortURLKey),longUrl);
            return newShortURL;
        }else{
            encode(longUrl);
        }

        return null;
    }

    /** Decodes a shortURL to an actual URL.
     * If short URL already exists then it regenerates the shortURL
     * @returns shortURL
     */
    public String decode(String shortUrl) {
        int urlLen = Integer.parseInt(shortUrlLen);
        return urlKeyMap.get(shortUrl.substring(shortUrl.length() - urlLen));
    }
}