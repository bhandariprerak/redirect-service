package com.url.shortener.redirect_service.repository;

import com.url.shortener.redirect_service.models.ClickEvent;
import com.url.shortener.redirect_service.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
    // Repository for managing ClickEvent entities, providing methods to retrieve click events based on URL mappings and date ranges.

    // Finds click events for a specific UrlMapping within the given date range.
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate, LocalDateTime endDate);

    // Finds click events for a list of UrlMappings within the given date range.
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings, LocalDateTime startDate, LocalDateTime endDate);
}