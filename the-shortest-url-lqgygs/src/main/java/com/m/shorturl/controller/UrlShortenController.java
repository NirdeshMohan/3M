package com.m.shorturl.controller;

import com.m.shorturl.model.ShortUrlRequest;
import com.m.shorturl.model.ShortUrlResponse;
import com.m.shorturl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UrlShortenController {

    @Autowired
    private UrlService urlService;

    @GetMapping(path = "/decode", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortUrlResponse> getShort3MURL(@RequestBody ShortUrlRequest req){
        return urlService.getShortURL(req);
    }
    @PostMapping(path = "/encode", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShortUrlResponse> createShort3MURL(@RequestBody ShortUrlRequest req){
        return urlService.createShortURL(req);
    }
}
