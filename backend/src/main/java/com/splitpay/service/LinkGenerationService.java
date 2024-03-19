package com.splitpay.service;
import com.splitpay.model.Order;
import com.splitpay.payment.PaymentLinkContext;
import com.splitpay.payment.PicpayLinkStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LinkGenerationService {
  public Map<String, String> generatePaymentLinks(Order order) {
    Map<String, String> paymentLinksMap = new HashMap<>();

    order.getParticipants().forEach(participant -> {
      PaymentLinkContext context = new PaymentLinkContext(new PicpayLinkStrategy());
      String paymentLink = context.generateLink(participant);
      paymentLinksMap.put(participant.getName(), paymentLink);
    });

    return paymentLinksMap;
  }
}
