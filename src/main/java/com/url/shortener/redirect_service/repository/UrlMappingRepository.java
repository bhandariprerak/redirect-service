package com.url.shortener.redirect_service.repository;

import com.url.shortener.redirect_service.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing UrlMapping entities.
 * Provides methods to perform CRUD operations and custom queries related to URL mappings.
 */
@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    /**
     * Finds a UrlMapping entity by its short URL.
     *
     * @param shortUrl the short URL string to search for
     * @return the UrlMapping entity matching the given short URL, or null if none found
     */
    Optional<UrlMapping> findByShortUrl(String shortUrl);

}