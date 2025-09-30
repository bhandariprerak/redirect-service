package com.url.shortener.redirect_service.service;

import com.url.shortener.redirect_service.models.UrlMapping;
import com.url.shortener.redirect_service.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * Service class responsible for getting Original URL for redirection
 */
@Service
@AllArgsConstructor
public class UrlMappingService {

    private UrlMappingRepository urlMappingRepository;

    /**
     * Retrieves the original URL mapping for a given short URL, increments its click count,
     * and records a click event.
     *
     * @param shortUrl The short URL to resolve.
     * @return The UrlMapping entity, or null if not found.
     */
    public UrlMapping getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl)
                .map(mapping -> {
                    mapping.setClickCount(mapping.getClickCount() + 1);
                    return urlMappingRepository.save(mapping);
                })
                .orElse(null);
    }
}