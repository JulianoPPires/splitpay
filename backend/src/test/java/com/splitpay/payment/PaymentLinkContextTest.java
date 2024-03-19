package com.splitpay.payment;

import com.splitpay.model.Participant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaymentLinkContextTest {

  @Test
  public void testGenerateLink() {
    PaymentLinkStrategy strategyMock = Mockito.mock(PaymentLinkStrategy.class);

    Participant participant = new Participant();
    String expectedLink = "http://example.com/payment/john";
    when(strategyMock.generateLink(participant)).thenReturn(expectedLink);

    PaymentLinkContext context = new PaymentLinkContext(strategyMock);

    String generatedLink = context.generateLink(participant);
    assertEquals(expectedLink, generatedLink);
  }
}