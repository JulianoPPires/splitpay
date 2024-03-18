package com.splitpay.payment;

public class PaymentLinkContext {
  private PaymentLinkStrategy strategy;

  public PaymentLinkContext(PaymentLinkStrategy strategy) {
    this.strategy = strategy;
  }

  public String generateLink(String name, double amount) {
    return strategy.generateLink(name, amount);
  }
}