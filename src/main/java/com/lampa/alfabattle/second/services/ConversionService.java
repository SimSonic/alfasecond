package com.lampa.alfabattle.second.services;

import com.lampa.alfabattle.second.dto.PaymentCategoryInfo;
import com.lampa.alfabattle.second.dto.UserAnalyticEnd;
import com.lampa.alfabattle.second.dtokafka.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("conversion")
public class ConversionService {
    //key1:{"ref":"ref1", "categoryId":1, "userId":"User_1", "recipientId":"User_2", "desc":"Тестовый платеж_1", "amount":10.0}

    Map<String, List<PaymentCategoryInfo>> userPayments = new HashMap<>();

    public void convert(Payment payment) {
        if (!userPayments.containsKey(payment.getUserId())) {
            userPayments.put(payment.getUserId(), new ArrayList<>());
        }

        List<PaymentCategoryInfo> usercats = userPayments.get(payment.getUserId());

        PaymentCategoryInfo paymentCategoryInfo = new PaymentCategoryInfo();
        paymentCategoryInfo.setCategoryId(payment.getCategoryId());
        paymentCategoryInfo.setMin(payment.getAmount());
        paymentCategoryInfo.setMax(payment.getAmount());
        paymentCategoryInfo.setSum(payment.getAmount());

        if (usercats.contains(paymentCategoryInfo)) {
            usercats.get(usercats.indexOf(paymentCategoryInfo)).update(payment);
        } else {
            usercats.add(paymentCategoryInfo);
        }

        log.info("Received message {}", payment);
    }

    public List<UserAnalyticEnd> getAnalytics() {
        List<UserAnalyticEnd> ends = new ArrayList<>();

        for (Map.Entry<String, List<PaymentCategoryInfo>> entry : userPayments.entrySet()) {
            UserAnalyticEnd end = new UserAnalyticEnd();
            end.setUserId(entry.getKey());

            BigDecimal total = new BigDecimal("0");

            Map<Integer, PaymentCategoryInfo> map = new HashMap<>();
            for (PaymentCategoryInfo info : entry.getValue()) {
                map.put(info.getCategoryId(), info);
                total = total.add(info.getSum());
            }

            end.setAnalyticInfo(map);
            end.setTotalSum(total);

            ends.add(end);
        }

        return ends;
    }
}
