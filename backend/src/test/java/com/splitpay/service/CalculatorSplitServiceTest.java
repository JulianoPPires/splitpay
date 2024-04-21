package com.splitpay.service;

import com.splitpay.fixture.OrderFixture;
import com.splitpay.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CalculatorSplitServiceTest {

  @Autowired
  CalculatorSplitService calculatorSplitService;

  @BeforeEach
  void setUp() {

  }

  @Test
  void testCalculateSumTotalItemsOfOrder() {
    BigDecimal expectedValue = new BigDecimal("50");

    Order order = OrderFixture.createOrderEqualsExampleProblem();

    BigDecimal result = calculatorSplitService.calculateSumTotalItemsOfOrder(order);

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void testCalculateSumPriceTotalOfItemsArray1() {
    BigDecimal expectedValue = new BigDecimal("42");

    BigDecimal result = calculatorSplitService.calculateSumTotalValueItems( OrderFixture.createItemsParticipant1());

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void testCalculateSumPriceTotalOfItemsArray2() {
    BigDecimal expectedValue = new BigDecimal("8");

    BigDecimal result = calculatorSplitService.calculateSumTotalValueItems(OrderFixture.createItemsParticipant2());

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void testCalculateSumTotalIncreasesOfOrder() {
    BigDecimal expectedValue = new BigDecimal("8");

    BigDecimal result = calculatorSplitService.calculateTotalIncreasesOfOrder(OrderFixture.createOrderEqualsExampleProblem());

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void testCalculateTotalDiscountsOfOrder() {
    BigDecimal expectedValue = new BigDecimal("20");

    BigDecimal result = calculatorSplitService.calculateTotalDiscountsOfOrder(OrderFixture.createOrderEqualsExampleProblem());

    Assertions.assertEquals(expectedValue, result);
  }

  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder1() {
    BigDecimal expectedTotalValue = new BigDecimal("42");
    BigDecimal expectedIndividualPercentage = new BigDecimal("0.84");
    BigDecimal expectedTotalIndividualIncrease = new BigDecimal("6.72");
    BigDecimal expectedTotalIndividualDiscount = new BigDecimal("16.80");
    BigDecimal expectedDifferenceIncreaseAndDiscountIndividual = new BigDecimal("10.08");
    BigDecimal expectedTotalIndividualExpense = new BigDecimal("31.92");

    Order order = OrderFixture.createOrderEqualsExampleProblem();

    order = calculatorSplitService.calculateTotalIndividualOfOperation(order);

    Assertions.assertEquals(expectedTotalValue, order.getParticipants().get(0).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 84%
    Assertions.assertEquals(expectedIndividualPercentage, order.getParticipants().get(0).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(expectedTotalIndividualIncrease, order.getParticipants().get(0).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(expectedTotalIndividualDiscount, order.getParticipants().get(0).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(expectedDifferenceIncreaseAndDiscountIndividual, order.getParticipants().get(0).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(expectedTotalIndividualExpense, order.getParticipants().get(0).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testCalculateSumTotalValueItemsAndIndividualPercentageOfTotalOrder2() {
    Order order = OrderFixture.createOrderEqualsExampleProblem();

    BigDecimal expectedTotalValue = new BigDecimal("8");
    BigDecimal expectedIndividualPercentage = new BigDecimal("0.16");
    BigDecimal expectedTotalIndividualIncrease = new BigDecimal("1.28");
    BigDecimal expectedTotalIndividualDiscount = new BigDecimal("3.20");
    BigDecimal expectedDifferenceIncreaseAndDiscountIndividual = new BigDecimal("1.92");
    BigDecimal expectedTotalIndividualExpense = new BigDecimal("6.08");

    order = calculatorSplitService.calculateTotalIndividualOfOperation(order);

    Assertions.assertEquals(expectedTotalValue, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(expectedIndividualPercentage, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(expectedTotalIndividualIncrease, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(expectedTotalIndividualDiscount, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(expectedDifferenceIncreaseAndDiscountIndividual, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(expectedTotalIndividualExpense, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testWithValueIncrementsEqualZero() {
    Order order = OrderFixture.createOrderWithIncreasesEqualsZero();

    BigDecimal expectedTotalValue = new BigDecimal("8");
    BigDecimal expectedIndividualPercentage = new BigDecimal("0.16");
    BigDecimal expectedTotalIndividualIncrease = new BigDecimal("0.00");
    BigDecimal expectedTotalIndividualDiscount = new BigDecimal("3.20");
    BigDecimal expectedDifferenceIncreaseAndDiscountIndividual = new BigDecimal("3.20");
    BigDecimal expectedTotalIndividualExpense = new BigDecimal("4.80");

    order = calculatorSplitService.calculateTotalIndividualOfOperation(order);

    Assertions.assertEquals(expectedTotalValue, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(expectedIndividualPercentage, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(expectedTotalIndividualIncrease, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(expectedTotalIndividualDiscount, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(expectedDifferenceIncreaseAndDiscountIndividual, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(expectedTotalIndividualExpense, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

  @Test
  void testWithValueDistountsEqualZero() {
    Order order = OrderFixture.createOrderWithDiscountsEqualsZero();

    BigDecimal expectedTotalValue = new BigDecimal("8");
    BigDecimal expectedIndividualPercentage = new BigDecimal("0.16");
    BigDecimal expectedTotalIndividualIncrease = new BigDecimal("1.28");
    BigDecimal expectedTotalIndividualDiscount = new BigDecimal("0.00");
    BigDecimal expectedDifferenceIncreaseAndDiscountIndividual = new BigDecimal("1.28");
    BigDecimal expectedTotalIndividualExpense = new BigDecimal("9.28");

    order = calculatorSplitService.calculateTotalIndividualOfOperation(order);

    Assertions.assertEquals(expectedTotalValue, order.getParticipants().get(1).getFinancials().getSumValueTotalOfItems());
    //porcentagem individual em relacao ao total 16%
    Assertions.assertEquals(expectedIndividualPercentage, order.getParticipants().get(1).getFinancials().getIndividualPercentage());
    Assertions.assertEquals(expectedTotalIndividualIncrease, order.getParticipants().get(1).getFinancials().getTotalIndividualIncrease());
    Assertions.assertEquals(expectedTotalIndividualDiscount, order.getParticipants().get(1).getFinancials().getTotalIndividualDiscount());
    Assertions.assertEquals(expectedDifferenceIncreaseAndDiscountIndividual, order.getParticipants().get(1).getFinancials().getDifferenceIncreaseAndDiscountIndividual());
    Assertions.assertEquals(expectedTotalIndividualExpense, order.getParticipants().get(1).getFinancials().getTotalIndividualExpense());
  }

}