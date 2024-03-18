package com.splitpay.payment;

import com.splitpay.model.Participant;

public class PaymentLinkContext {
  private PaymentLinkStrategy strategy;

  public PaymentLinkContext(PaymentLinkStrategy strategy) {
    this.strategy = strategy;
  }

  public String generateLink(Participant participant) {
    return strategy.generateLink(participant);
  }
}