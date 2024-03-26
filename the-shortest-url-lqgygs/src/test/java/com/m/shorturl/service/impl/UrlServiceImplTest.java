package com.m.shorturl.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.m.shorturl.model.ShortUrlRequest;
import com.m.shorturl.model.ShortUrlResponse;
import com.m.shorturl.util.UrlShortner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UrlServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class UrlServiceImplTest {
    @Autowired
    private UrlServiceImpl urlServiceImpl;

    @MockBean
    private UrlShortner urlShortner;

    @Test
    void testGetShortURL() {
        when(urlShortner.decode(Mockito.<String>any())).thenReturn("https://www.google.com/example");
        ResponseEntity<ShortUrlResponse> actualShortURL = urlServiceImpl
                .getShortURL(new ShortUrlRequest("https://www.google.com/example"));
        verify(urlShortner).decode(eq("https://www.google.com/example"));
        ShortUrlResponse body = actualShortURL.getBody();
        assert body != null;
        assertEquals("https://www.google.com/example", body.getOriginalUrl());
        assertEquals("https://www.google.com/example", body.getShortUrl());
        assertEquals(200, actualShortURL.getStatusCode().value());
    }

    @Test
    void testGetShortURLNoData() {
        when(urlShortner.decode(Mockito.<String>any())).thenReturn("");
        ResponseEntity<ShortUrlResponse> actualShortURL = urlServiceImpl
                .getShortURL(new ShortUrlRequest("https://www.google.com/example"));
        verify(urlShortner).decode(eq("https://www.google.com/example"));
        ShortUrlResponse body = actualShortURL.getBody();
        assert body != null;
        assertEquals("NO DATA FOUND", body.getShortUrl());
        assertEquals("https://www.google.com/example", body.getOriginalUrl());
        assertEquals(204, actualShortURL.getStatusCode().value());
    }

    @Test
    void testGetShortURLNoContent() {
        ResponseEntity<ShortUrlResponse> actualShortURL = urlServiceImpl.getShortURL(new ShortUrlRequest("UU"));

        ShortUrlResponse body = actualShortURL.getBody();
        assert body != null;
        assertEquals("UU", body.getOriginalUrl());
        assertNull(body.getShortUrl());
        assertEquals(400, actualShortURL.getStatusCode().value());
        assertTrue(actualShortURL.hasBody());
        assertTrue(actualShortURL.getHeaders().isEmpty());
    }

    @Test
    void testCreateShortURL() {
        when(urlShortner.encode(Mockito.<String>any())).thenReturn("https://www.google.com/example");

        ResponseEntity<ShortUrlResponse> actualCreateShortURLResult = urlServiceImpl
                .createShortURL(new ShortUrlRequest("https://www.google.com/example"));
        verify(urlShortner).encode(eq("https://www.google.com/example"));
        ShortUrlResponse body = actualCreateShortURLResult.getBody();
        assert body != null;
        assertEquals("https://www.google.com/example", body.getOriginalUrl());
        assertEquals("https://www.google.com/example", body.getShortUrl());
        assertEquals(200, actualCreateShortURLResult.getStatusCode().value());
    }

    @Test
    void testCreateShortBadRequest() {
        ResponseEntity<ShortUrlResponse> actualCreateShortURLResult = urlServiceImpl
                .createShortURL(new ShortUrlRequest("UU"));
        ShortUrlResponse body = actualCreateShortURLResult.getBody();
        assert body != null;
        assertEquals("BAD INPUT", body.getShortUrl());
        assertEquals("UU", body.getOriginalUrl());
        assertEquals(400, actualCreateShortURLResult.getStatusCode().value());
    }

//    @Test
//    void testDeleteOriginalURL() {
//        assertNull(urlServiceImpl.deleteOriginalURL(new ShortUrlRequest("https://example.org/example")));
//        assertNull(urlServiceImpl.deleteOriginalURL(mock(ShortUrlRequest.class)));
//    }
}
