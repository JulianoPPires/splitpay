package com.splitpay.payment;

public interface PaymentLinkStrategy {
  String generateLink(String name, double amount);
}