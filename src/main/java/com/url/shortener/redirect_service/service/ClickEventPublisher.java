package com.url.shortener.redirect_service.service;

import com.url.shortener.redirect_service.dto.ClickEventRmqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClickEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void sendClickEvent(ClickEventRmqDTO message) {
        rabbitTemplate.convertAndSend("click-events-queue", message);
    }
}
