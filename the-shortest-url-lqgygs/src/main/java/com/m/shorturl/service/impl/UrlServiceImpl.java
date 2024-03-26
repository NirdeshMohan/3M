package com.m.shorturl.service.impl;

import com.m.shorturl.model.ShortUrlRequest;
import com.m.shorturl.model.ShortUrlResponse;
import com.m.shorturl.service.UrlService;
import com.m.shorturl.util.UrlShortner;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlShortner urlShortner;
    @Override
    public ResponseEntity<ShortUrlResponse> getShortURL(ShortUrlRequest req){
        UrlValidator validator = new UrlValidator();
        if(!validator.isValid(req.getOriginalUrl())){
            return new ResponseEntity<>(new ShortUrlResponse((req.getOriginalUrl()), null), HttpStatus.BAD_REQUEST);
        }
        String shortURL = urlShortner.decode(req.getOriginalUrl());
        if(null == shortURL || shortURL.isEmpty()){
            return new ResponseEntity<>(new ShortUrlResponse(req.getOriginalUrl(), "NO DATA FOUND"), HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new ShortUrlResponse(shortURL, req.getOriginalUrl()), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<ShortUrlResponse> createShortURL(ShortUrlRequest req){
        UrlValidator validator = new UrlValidator();
        if(!validator.isValid(req.getOriginalUrl())){
            return new ResponseEntity<>(new ShortUrlResponse(req.getOriginalUrl()), HttpStatus.BAD_REQUEST);
        }
        String shortURL = urlShortner.encode(req.getOriginalUrl());
        return new ResponseEntity<>(new ShortUrlResponse(req.getOriginalUrl(), shortURL), HttpStatus.OK);
    }
//    @Override
//    public ResponseEntity<ShortUrlResponse> deleteOriginalURL(ShortUrlRequest req){return null; }
}
