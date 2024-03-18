package com.splitpay.payment;

import com.splitpay.model.Participant;

public class PicpayLinkStrategy implements PaymentLinkStrategy {
  @Override
  public String generateLink(Participant participant) {
    return "https://picpay.me/"+participant.getName()+"/" + participant.getFinancials().getTotalIndividualExpense();
  }
}
