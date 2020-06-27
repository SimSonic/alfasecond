package com.lampa.alfabattle.second.controllers;

import com.lampa.alfabattle.second.dto.PaymentCategoryInfo;
import com.lampa.alfabattle.second.dto.UserAnalyticEnd;
import com.lampa.alfabattle.second.dto.UserPaymentStats;
import com.lampa.alfabattle.second.dto.UserTemplate;
import com.lampa.alfabattle.second.services.AnalyticsService;
import com.lampa.alfabattle.second.services.ConversionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {
    private ConversionService conversionService;
    private AnalyticsService analyticsService;

    @Autowired
    public AnalyticsController(ConversionService conversionService, AnalyticsService analyticsService) {
        this.conversionService = conversionService;
        this.analyticsService = analyticsService;
    }

    @GetMapping("/analytic")
    private List<UserAnalyticEnd> get() {
        return conversionService.getAnalytics();
    }

    @GetMapping("/analytic/{userId}")
    private ResponseEntity getUserAnalytics(@PathVariable("userId") String userId) throws ResponseStatusException {

        for (UserAnalyticEnd end : conversionService.getAnalytics()) {
            if (end.getUserId().equals(userId)) {
                return new ResponseEntity<>(end, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error(), HttpStatus.NOT_FOUND);
    }


    @GetMapping("/analytic/{userId}/stats")
    private ResponseEntity getUserAnalyticsStats(@PathVariable("userId") String userId) throws ResponseStatusException {
        for (UserAnalyticEnd end : conversionService.getAnalytics()) {
            if (end.getUserId().equals(userId)) {
                Map<Integer, PaymentCategoryInfo> analyticInfo = end.getAnalyticInfo();

                Integer maxAmountCategoryId = 0;
                Integer minAmountCategoryId = 0;

                Integer oftenCategoryId = 0;
                Integer rareCategoryId = 0;

                BigDecimal maxAmount = new BigDecimal(0);
                BigDecimal minAmount = new BigDecimal(1111111110);

                Integer maxOften = 0;
                Integer minRare = 10000;

                for (PaymentCategoryInfo info : analyticInfo.values()) {
                    if (info.getUseCount() > maxOften) {
                        maxOften = info.getUseCount();
                        oftenCategoryId = info.getCategoryId();
                    }

                    if (info.getUseCount() < minRare) {
                        minRare = info.getUseCount();
                        rareCategoryId = info.getCategoryId();
                    }

                    if (info.getSum().compareTo(maxAmount) > 0) {
                        maxAmount = info.getSum();
                        maxAmountCategoryId = info.getCategoryId();
                    }

                    if (info.getSum().compareTo(minAmount) < 0) {
                        minAmount = info.getSum();
                        minAmountCategoryId = info.getCategoryId();
                    }

                }

                UserPaymentStats stats = new UserPaymentStats();
                stats.setOftenCategoryId(oftenCategoryId);
                stats.setRareCategoryId(rareCategoryId);
                stats.setMinAmountCategoryId(minAmountCategoryId);
                stats.setMaxAmountCategoryId(maxAmountCategoryId);


                return new ResponseEntity<>(stats, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/analytic/{userId}/templates")
    private ResponseEntity getUserAnalyticsTemplates(@PathVariable("userId") String userId) throws ResponseStatusException {
        for (UserAnalyticEnd end : conversionService.getAnalytics()) {
            if (end.getUserId().equals(userId)) {
                Map<Integer, PaymentCategoryInfo> analyticInfo = end.getAnalyticInfo();

                List<UserTemplate> templates = new ArrayList<>();

                for (PaymentCategoryInfo info : analyticInfo.values()) {
                    if (info.getUseCount() > 3) {
                        UserTemplate userTemplate = new UserTemplate();
                        userTemplate.setAmount(info.getSum());
                        userTemplate.setCategoryId(info.getCategoryId());
                        //userTemplate.setRecipientId(info.g);
                        templates.add(userTemplate);
                    }
                }

                return new ResponseEntity<>(templates, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(new Error(), HttpStatus.NOT_FOUND);
    }

    @Data
    private static class Error {
        private String status = "user not found";
    }
}
