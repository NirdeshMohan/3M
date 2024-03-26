package com.m.shorturl.service;

import com.m.shorturl.model.ShortUrlRequest;
import com.m.shorturl.model.ShortUrlResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public ResponseEntity<ShortUrlResponse> getShortURL(ShortUrlRequest req);

    public ResponseEntity<ShortUrlResponse> createShortURL(ShortUrlRequest req);

//    public ResponseEntity<ShortUrlResponse> deleteOriginalURL(ShortUrlRequest req);
}
