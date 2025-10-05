package com.url.shortener.redirect_service.controller;

import com.url.shortener.redirect_service.dto.ClickEventRmqDTO;
import com.url.shortener.redirect_service.models.UrlMapping;
import com.url.shortener.redirect_service.service.ClickEventPublisher;
import com.url.shortener.redirect_service.service.UrlMappingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class RedirectController {

    private final UrlMappingService urlMappingService;
    private final ClickEventPublisher clickEventProducer;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);
        if (urlMapping != null) {
            ClickEventRmqDTO message = new ClickEventRmqDTO(
                    urlMapping.getId(),
                    LocalDateTime.now()
            );
            clickEventProducer.sendClickEvent(message);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", urlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}