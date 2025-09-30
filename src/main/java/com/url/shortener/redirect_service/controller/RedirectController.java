package com.url.shortener.redirect_service.controller;

import com.url.shortener.redirect_service.models.UrlMapping;
import com.url.shortener.redirect_service.models.ClickEvent;
import com.url.shortener.redirect_service.repository.ClickEventRepository;
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
    private final ClickEventRepository clickEventRepository;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlMapping urlMapping = urlMappingService.getOriginalUrl(shortUrl);
        if (urlMapping != null) {
            // Create and save ClickEvent
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setUrlMapping(urlMapping);
            clickEvent.setClickDate(LocalDateTime.now());
            clickEventRepository.save(clickEvent);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Location", urlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}