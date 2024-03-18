package com.splitpay.payment;

import com.splitpay.model.Participant;

public interface PaymentLinkStrategy {
  String generateLink(Participant participant);
}