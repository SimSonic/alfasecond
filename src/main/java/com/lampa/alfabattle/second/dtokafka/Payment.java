package com.lampa.alfabattle.second.dtokafka;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    private String ref;
    private Integer categoryId;
    private String userId;
    private String recipientId;
    private String desc;
    private BigDecimal amount;
}
