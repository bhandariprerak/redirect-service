package com.url.shortener.redirect_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickEventRmqDTO {
    private Long urlMappingId;   // corresponds to url_mapping.id
    private LocalDateTime clickDate;
}