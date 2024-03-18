package com.splitpay.service;

import com.splitpay.fixture.OrderFixture;
import com.splitpay.model.Order;
import com.splitpay.model.Participant;
import com.splitpay.model.ParticipantFinancials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SplitOrderServiceTest {

  @Autowired
  SplitOrderService producerService;

  @BeforeEach
  void setUp() {

  }

  @Test
  void testCalculateSumTotalItemsOfOrder() {

    Order order = OrderFixture.createOrder();

    double result = producerService.calculateSumTotalItemsOfOrder(order);

    Assertions.assertEquals(50.0, result);
  }

  @Test
  void testCalculateSumPriceTotalOfItemsArray1() {

    double result = producerService.calculateSumTotalValueItems( OrderFixture.createItemsParticipant1());

    Assertions.assertEquals(42.0, result);
  }

  @Test
  void testCalculateSumPriceTotalOfItemsArray2() {

    double result = producerService.calculateSumTotalValueItems(OrderFixture.createItemsParticipant2());

    Assertions.assertEquals(8, result);
  }

  @Test
  void testCalculateSumTotalIncreasesOfOrder() {

    double result = producerService.calculateTotalIncreasesOfOrder(OrderFixture.createOrder());

    Assertions.assertEquals(8.0, result);
  }

  @Test
  void testCalculateTotalDiscountsOfOrder() {

    double result = producerService.calculateTotalDiscountsOfOrder(OrderFixture.createOrder());

    Assertions.assertEquals(20.0, result);
  }

/*  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder() {
    Order order = OrderFixture.createOrder();
    Participant participant = order.getParticipants().get(0);
    ParticipantFinancials financials = participant.getFinancials();
    financials.setSumValueTotalOfItems(producerService.calculateSumTotalValueItems(participant.getItems()));

    producerService.calculateIndividualPercentageOfTotal(financials, producerService.calculateSumTotalItemsOfOrder(order));


    Assertions.assertEquals(42.0, participant.getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 84%
    Assertions.assertEquals(0.84, participant.getFinancials().getIndividualPercentage());
  }

  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder2() {
    Order order = OrderFixture.createOrder();
    Participant participant = order.getParticipants().get(1);
    ParticipantFinancials financials = participant.getFinancials();
    financials.setSumValueTotalOfItems(producerService.calculateSumTotalValueItems(participant.getItems()));

    producerService.calculateIndividualPercentageOfTotal(financials, producerService.calculateSumTotalItemsOfOrder(order));

    Assertions.assertEquals(8, participant.getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(0.16, participant.getFinancials().getIndividualPercentage());
  }*/



}