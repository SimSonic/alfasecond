package com.lampa.alfabattle.second.config;

import com.lampa.alfabattle.second.dtokafka.Payment;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class CustomDeserializer extends JsonDeserializer<Payment> {
    @Override
    public Payment deserialize(String topic, Headers headers, byte[] data) {
        try {
            return super.deserialize(topic, headers, data);
        } catch (Exception ex) {
            return null;
        }
    }
}
