package com.m.shorturl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.shorturl.model.ShortUrlRequest;
import com.m.shorturl.model.ShortUrlResponse;
import com.m.shorturl.service.UrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(value = UrlShortenController.class)
public class UrlShortenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UrlService  service;

    @InjectMocks
    private UrlShortenController urlShortenController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private ShortUrlResponse mockResponse;

    @MockBean
    ObjectMapper objectMapper;

    @Test
    void getShort3MURLNoContent() throws Exception {
        Mockito.when(service.getShortURL(new ShortUrlRequest("http://short3murl.com/ABcDe/" )))
                .thenReturn(new ResponseEntity<>(new ShortUrlResponse("http://www.google.com","http://short3murl.com/ABcDe/"), HttpStatus.OK));

        ShortUrlRequest shortUrlRequest = new ShortUrlRequest();
        shortUrlRequest.setOriginalUrl("http://short3murl.com/ABcDe/");
        ObjectMapper mapper = new ObjectMapper();
        String jsonReq = mapper.writeValueAsString(shortUrlRequest);

        assertEquals(204,
                (mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/api/v1/decode", new ShortUrlRequest("http://short3murl.com/ABcDe/" ))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonReq))
                                .andReturn()
                                .getResponse()
                                .getStatus()));
    }

    @Test
    void getShort3MURLBadInput() throws Exception {
        Mockito.when(service.getShortURL(new ShortUrlRequest("http://short3murl.com/ABcDe/" )))
                .thenReturn(new ResponseEntity<>(new ShortUrlResponse("http://www.google.com","http://short3murl.com/ABcDe/"), HttpStatus.OK));

        ShortUrlRequest shortUrlRequest = new ShortUrlRequest();
        shortUrlRequest.setOriginalUrl("//short3murl.com/ABcDe/");
        ObjectMapper mapper = new ObjectMapper();
        String jsonReq = mapper.writeValueAsString(shortUrlRequest);

        assertEquals(400,
                (mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/api/v1/decode", new ShortUrlRequest("http://short3murl.com/ABcDe/" ))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonReq))
                        .andReturn()
                        .getResponse()
                        .getStatus()));
    }

    @Test
    void getShort3MURLNotFound() throws Exception {
        ShortUrlRequest shortUrlRequest = new ShortUrlRequest();
        shortUrlRequest.setOriginalUrl("http://shorturl.com/ABcDe/");
        ObjectMapper mapper = new ObjectMapper();
        String jsonReq = mapper.writeValueAsString(shortUrlRequest);

        assertEquals(404,
                (mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/api/v1/decodee", new ShortUrlRequest("http:////short3murl.com/ABcDe/" ))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonReq))
                        .andReturn()
                        .getResponse()
                        .getStatus()));
    }

    @Test
    void createShort3MURL() throws Exception {
        Mockito.when(service.createShortURL(new ShortUrlRequest("http://www.google.com" )))
                .thenReturn(new ResponseEntity<>(new ShortUrlResponse("http://www.google.com","http://short3murl.com/ABcDe/"), HttpStatus.OK));

        ShortUrlRequest shortUrlRequest = new ShortUrlRequest();
        shortUrlRequest.setOriginalUrl("http://www.short3murl.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonReq = mapper.writeValueAsString(shortUrlRequest);

        assertEquals(200,
                (mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,"/api/v1/encode", new ShortUrlRequest("http://www.google.com" ))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(jsonReq))
                        .andReturn()
                        .getResponse()
                        .getStatus()));
    }

}