package com.splitpay.fixture;

import com.splitpay.dto.OrderResponseDto;

import java.util.HashMap;
import java.util.Map;

public class OrderResponseDtoFixture {

  public static OrderResponseDto createOrderResponse() {

    Map<String, String> paymentLinks = new HashMap<>();
    paymentLinks.put("Nome Participante 1", "http://example.com/link1");
    paymentLinks.put("Nome Participante 2", "http://example.com/link2");

    return new OrderResponseDto(paymentLinks);
  }
}
