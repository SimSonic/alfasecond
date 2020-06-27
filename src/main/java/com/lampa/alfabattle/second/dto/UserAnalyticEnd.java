package com.lampa.alfabattle.second.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserAnalyticEnd {
    private String userId;
    private BigDecimal totalSum;
    private Map<Integer, PaymentCategoryInfo> analyticInfo = new HashMap<>();
}
