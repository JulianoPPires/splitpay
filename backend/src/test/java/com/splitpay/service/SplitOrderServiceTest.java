package com.splitpay.service;

import com.splitpay.fixture.OrderFixture;
import com.splitpay.model.Order;
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

    Order order = OrderFixture.createOrderEqualsExampleProblem();

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

    double result = producerService.calculateTotalIncreasesOfOrder(OrderFixture.createOrderEqualsExampleProblem());

    Assertions.assertEquals(8.0, result);
  }

  @Test
  void testCalculateTotalDiscountsOfOrder() {

    double result = producerService.calculateTotalDiscountsOfOrder(OrderFixture.createOrderEqualsExampleProblem());

    Assertions.assertEquals(20.0, result);
  }

  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder1() {
    Order order = OrderFixture.createOrderEqualsExampleProblem();

    order = producerService.caculateTotalIndividualOfIncreasesAndDiscounts(order);

    Assertions.assertEquals(42.0, order.getParticipants().get(0).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 84%
    Assertions.assertEquals(0.84, order.getParticipants().get(0).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(6.72, order.getParticipants().get(0).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(16.8, order.getParticipants().get(0).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(10.08, order.getParticipants().get(0).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(31.92, order.getParticipants().get(0).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder2() {
    Order order = OrderFixture.createOrderEqualsExampleProblem();

    order = producerService.caculateTotalIndividualOfIncreasesAndDiscounts(order);

    Assertions.assertEquals(8.0, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(0.16, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(1.28, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(3.2, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(1.92, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(6.08, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testWithValueIncrementsEqualZero() {
    Order order = OrderFixture.createOrderWithIncreasesEqualsZero();

    order = producerService.caculateTotalIndividualOfIncreasesAndDiscounts(order);

    Assertions.assertEquals(8.0, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(0.16, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(0, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(3.2, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(3.2, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(4.8, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testWithValueDistountsEqualZero() {
    Order order = OrderFixture.createOrderWithDiscountsEqualsZero();

    order = producerService.caculateTotalIndividualOfIncreasesAndDiscounts(order);

    Assertions.assertEquals(8.0, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(0.16, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(1.28, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(0, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(1.28, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(6.72, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

//teste para aceitar sem discontos
//teste para aceitar sem incrementos
//teste para nao aceitar valores negativos
//teste ao menos 2 participantes para a divisao
}