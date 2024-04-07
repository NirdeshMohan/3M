package com.m.shorturl.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UrlShortner {
    //Map to store the shortURL key and actual LongURL
    private final Map<String,String> urlKeyMap = new HashMap<>();
    private final Random rand = new Random();
    @Value("${short.url.max.length}")
    private int shortUrlLen;

    /** Encodes a URL to a shortened URL.
     * If short URL already exists then it regenerates the shortURL
     * @returns shortURL
     */
    public String encode(String url) {
        //Base64.getUrlEncoder().encodeToString(longUrl.getBytes());
        //Base64.getUrlEncoder().encodeToString(longUrl.getBytes());
        String newShortURL;
        char [] shortURLKey = new char[shortUrlLen];
        String base64Chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/";

        for(int i = 0; i < shortUrlLen; i++ ) {
            shortURLKey[i] = base64Chars.charAt(rand.nextInt(base64Chars.length()));
        }

        if(urlKeyMap.containsValue(String.valueOf(shortURLKey))){
            //In case key is already exists
            encode(url);
        }else{
            String short3MURL = "http://short3murl.com/";
            newShortURL = short3MURL + String.valueOf(shortURLKey);
            urlKeyMap.put(String.valueOf(shortURLKey),url);
            return newShortURL;
        }

//        if(!shortUrlKeyList.contains(String.copyValueOf(shortURLKey))){
//            newShortURL = short3MURL + String.copyValueOf(shortURLKey);
//            shortUrlKeyList.add(String.copyValueOf(shortURLKey));
//            urlKeyMap.put(String.copyValueOf(shortURLKey),url);
//            return newShortURL;
//        }else{
//            //In case key is already exists
//            encode(url);
//        }

        return null;
    }

    /** Decodes a shortURL to an actual URL.
     * If short URL already exists then it regenerates the shortURL
     * @returns shortURL
     */
    public String decode(String url) {
        return urlKeyMap.get(url.substring(url.length() - shortUrlLen));
    }
}
