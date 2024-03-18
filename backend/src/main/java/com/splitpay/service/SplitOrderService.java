package com.splitpay.service;

import com.splitpay.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitOrderService {


  public void calculateTotalPaidByEachParticipant(Order order) {

    order = this.caculateTotalIndividualOfIncreasesAndDiscounts(order);
    order.getParticipants().forEach(participant -> {
      System.out.println(participant.getName() + ": R$" + participant.getFinancials().getTotalIndividualExpense());
    });

  }

  protected Order caculateTotalIndividualOfIncreasesAndDiscounts(Order order) {

    double totalValueItemsOfOrder = calculateSumTotalItemsOfOrder(order);
    double totalIncreases = calculateTotalIncreasesOfOrder(order);
    double totalDiscounts = calculateTotalDiscountsOfOrder(order);

    order.getParticipants()
        .forEach(participant -> {
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

    double totalPaidParticipant = financials.getSumValueTotalOfItems() - financials.getDifferenceIncreaseAndDiscountIndividual();
    double totalIndividualExpense = Math.round(totalPaidParticipant * 100.0) / 100.0;

    financials.setTotalIndividualExpense(totalIndividualExpense);
  }

  private void calculateTotalIndividualIncrease(ParticipantFinancials financials, double totalIncreases) {

    double totalIndividualIncrease = financials.getIndividualPercentage() * totalIncreases;
    financials.setTotalIndividualIncrease(totalIndividualIncrease);
  }

  private void calculateIndividualPercentageOfTotal(ParticipantFinancials financials,
                                                    double totalValueItemsOfOrder) {

    double individualPercentage = financials.getSumValueTotalOfItems() / totalValueItemsOfOrder;
    financials.setIndividualPercentage(individualPercentage);
  }

  protected double calculateTotalIncreasesOfOrder(Order order) {

    if (order.getIncreases() != null) {
      return order.getIncreases().stream().mapToDouble(Increase::getValue).sum();
    }
    return 0;
  }

  protected double calculateTotalDiscountsOfOrder(Order order) {
    if (order.getDiscounts() != null) {
      return order.getDiscounts().stream().mapToDouble(Discount::getValue).sum();
    }else return 0;
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
