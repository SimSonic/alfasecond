package com.lampa.alfabattle.second.services;

import com.lampa.alfabattle.second.dtokafka.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {

    @Autowired
    private ConversionService conversionService;


    @KafkaListener(topics = "RAW_PAYMENTS", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
    public void listenRaw(Payment payment) {
        payment = payment;
        conversionService.convert(payment);
    }
}
