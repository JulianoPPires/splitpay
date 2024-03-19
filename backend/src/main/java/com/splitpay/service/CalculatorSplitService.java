package com.splitpay.service;

import com.splitpay.dto.OrderResponseDto;
import com.splitpay.model.*;
import com.splitpay.payment.PaymentLinkContext;
import com.splitpay.payment.PicpayLinkStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorSplitService {

  protected Order calculateTotalIndividualOfIncreasesAndDiscounts(Order order) {
    double totalValueItemsOfOrder = calculateSumTotalItemsOfOrder(order);
    double totalIncreases = calculateTotalIncreasesOfOrder(order);
    double totalDiscounts = calculateTotalDiscountsOfOrder(order);

    order.getParticipants().forEach(participant -> {
      ParticipantFinancials financials = participant.getFinancials();
      financials.setSumValueTotalOfItems(calculateSumTotalValueItems(participant.getItems()));
      calculateIndividualPercentageOfTotal(financials, totalValueItemsOfOrder);
      calculateTotalIndividualIncrease(financials, totalIncreases);
      calculateTotalIndividualDiscount(financials, totalDiscounts);
      calculateDifferenceIncreaseAndDiscount(financials);
      calculateTotalIndividualExpense(financials);
    });

    return order;
  }

  private void calculateTotalIndividualDiscount(ParticipantFinancials financials, double totalDiscounts) {
    double totalIndividualDiscount = financials.getIndividualPercentage() * totalDiscounts;
    financials.setTotalIndividualDiscount(totalIndividualDiscount);
  }

  private void calculateDifferenceIncreaseAndDiscount(ParticipantFinancials financials) {
    double differenceIncreaseAndDiscount = financials.getTotalIndividualIncrease() - financials.getTotalIndividualDiscount();
    double formattedDifference = Math.round(Math.abs(differenceIncreaseAndDiscount) * 100.0) / 100.0;
    financials.setDifferenceIncreaseAndDiscountIndividual(formattedDifference);
  }

  private void calculateTotalIndividualExpense(ParticipantFinancials financials) {
    double totalPaidParticipant = (financials.getSumValueTotalOfItems() + financials.getTotalIndividualIncrease()) - financials.getTotalIndividualDiscount();
    double totalIndividualExpense = Math.round(totalPaidParticipant * 100.0) / 100.0;
    financials.setTotalIndividualExpense(totalIndividualExpense);
  }

  private void calculateTotalIndividualIncrease(ParticipantFinancials financials, double totalIncreases) {
    double totalIndividualIncrease = financials.getIndividualPercentage() * totalIncreases;
    financials.setTotalIndividualIncrease(totalIndividualIncrease);
  }

  private void calculateIndividualPercentageOfTotal(ParticipantFinancials financials, double totalValueItemsOfOrder) {
    double individualPercentage = financials.getSumValueTotalOfItems() / totalValueItemsOfOrder;
    financials.setIndividualPercentage(individualPercentage);
  }

  protected double calculateTotalIncreasesOfOrder(Order order) {
    double totalIncreases = 0.0;

    if (order.getIncreases() != null) {
      for (Increase increase : order.getIncreases()) {
        if (increase.isPercentage()) {
          totalIncreases += (increase.getValue()) * calculateSumTotalItemsOfOrder(order);
        } else {
          totalIncreases += increase.getValue();
        }
      }
    }

    return totalIncreases;
  }

  protected double calculateTotalDiscountsOfOrder(Order order) {
    double totalDiscounts = 0.0;

    if (order.getDiscounts() != null) {
      for (Discount discount : order.getDiscounts()) {
        if (discount.isPercentage()) {
          totalDiscounts += (discount.getValue()) * calculateSumTotalItemsOfOrder(order);
        } else {
          totalDiscounts += discount.getValue();
        }
      }
    }

    return totalDiscounts;
  }

  protected double calculateSumTotalValueItems(List<Item> items) {
    return items.stream()
        .mapToDouble(Item::getValue)
        .sum();
  }

  protected double calculateSumTotalItemsOfOrder(Order order) {
    return order.getParticipants().stream()
        .flatMap(participant -> participant.getItems().stream())
        .mapToDouble(Item::getValue)
        .sum();
  }
}
