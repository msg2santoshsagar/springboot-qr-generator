package com.alife.qrcodegenerator.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
class ShutdownLogger implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("🛑 QR Code generator application stopped at {}", Instant.now());
    }
}