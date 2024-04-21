package com.splitpay.payment;

import com.splitpay.model.Participant;
import com.splitpay.model.ParticipantFinancials;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PicpayLinkStrategyTest {

  @Test
  public void testGenerateLink() {
    BigDecimal valor = new BigDecimal(100);
    ParticipantFinancials financials = new ParticipantFinancials();
    financials.setTotalIndividualExpense(valor);

    Participant participant = new Participant("John", null, financials);

    PicpayLinkStrategy strategy = new PicpayLinkStrategy();

    String generatedLink = strategy.generateLink(participant);

    assertEquals("https://picpay.me/John/100", generatedLink);
  }
}