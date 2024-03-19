package com.splitpay.payment;

import com.splitpay.model.Participant;
import com.splitpay.model.ParticipantFinancials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PicpayLinkStrategyTest {

  @Test
  public void testGenerateLink() {

    ParticipantFinancials financials = new ParticipantFinancials();
    financials.setTotalIndividualExpense(100);

    Participant participant = new Participant("John", null, financials);

    PicpayLinkStrategy strategy = new PicpayLinkStrategy();

    String generatedLink = strategy.generateLink(participant);

    assertEquals("https://picpay.me/John/100.0", generatedLink);
  }
}