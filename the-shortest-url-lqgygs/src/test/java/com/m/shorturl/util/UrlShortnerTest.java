package com.m.shorturl.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
class UrlShortnerTest {
    private static UrlShortner shortner;
    private static List<String> shortUrlKeyList;
    private static Map<String,String> urlKeyMap;
    private static String short3MURL;
    private static String shortUrlLen;
    private static Random rand;
    private static int shortUrlSize = 0;
    @BeforeAll
    public static void initEach(){
        shortner = new UrlShortner();
        shortUrlKeyList = new ArrayList<>();
        urlKeyMap = new HashMap<>();
        short3MURL = "http://short3murl.com/";
        shortUrlLen = "6";
        rand = new Random();

        shortUrlKeyList.add("ABcDe/");
        urlKeyMap.put("ABcDe/","http://google.com");
        shortUrlSize = Integer.parseInt(shortUrlLen);

        shortner.setShortUrlLen("6");
        shortner.setShortUrlKeyList(shortUrlKeyList);
        shortner.setUrlKeyMap(urlKeyMap);
    }

    @Test
    void urlShortnerNotNULL(){
        assertNotNull(this.shortner);
    }


    @Test
    void encode() {
        String url = "http://google.com";
        String shortURL = short3MURL + shortUrlKeyList.get(0);
        assertEquals("http://google.com",urlKeyMap.get(shortUrlKeyList.get(0)));
        assertNotNull(shortner.encode(url));
    }

    @Test
    void decode() {
        String shortURL = short3MURL + shortUrlKeyList.get(0);
        assertEquals("http://google.com", urlKeyMap.get(shortUrlKeyList.get(0)));
        assertEquals(urlKeyMap.get(shortUrlKeyList.get(0)), shortner.decode(shortURL));
    }
}