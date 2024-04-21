package com.splitpay.service;


import com.splitpay.dto.OrderResponseDto;
import com.splitpay.model.Order;
import org.springframework.stereotype.Service;

@Service
public class SplitService {

  private final CalculatorSplitService calculatorService;
  private final LinkGenerationService linkGenerationService;

  public SplitService(CalculatorSplitService calculatorService, LinkGenerationService linkGenerationService) {

    this.calculatorService = calculatorService;
    this.linkGenerationService = linkGenerationService;
  }

  public OrderResponseDto calculateTotalPaidByEachParticipantAndGenerateLinkToPayment(Order order) {

    order = calculatorService.calculateTotalIndividualOfOperation(order);
    return new OrderResponseDto(linkGenerationService.generatePaymentLinks(calculatorService.calculateTotalIndividualOfOperation(order)));
  }
}
