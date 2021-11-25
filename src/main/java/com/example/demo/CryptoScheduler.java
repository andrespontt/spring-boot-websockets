package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CryptoScheduler {
    private final static Logger log = LoggerFactory.getLogger(CryptoScheduler.class);

    @Autowired
    private SimpMessagingTemplate template;

    
    @Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CryptoData> forEntity = restTemplate.getForEntity("https://data.messari.io/api/v1/assets/btc/metrics", CryptoData.class);
        log.info("The time is now {}", forEntity.getBody().getData().getMarketData().getPriceUsd());
        TextMessage textMessage = new TextMessage();
        textMessage.setMessage(forEntity.getBody().getData().getMarketData().getPriceUsd());
        template.convertAndSend("/topic/message", textMessage);

	}

}
