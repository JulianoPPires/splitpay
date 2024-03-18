package com.splitpay.payment;

public class PicpayLinkStrategy implements PaymentLinkStrategy {
  @Override
  public String generateLink(String name, double amount) {
    return "https://picpay.me/"+name+"/" + amount;
  }
}
