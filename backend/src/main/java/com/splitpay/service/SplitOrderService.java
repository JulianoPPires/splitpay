package com.splitpay.service;

import com.splitpay.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitOrderService {


  public void calculateTotalPaidByEachParticipant(Order order) {
    this.caculateTotalIndividualOfIncreasesAndDiscounts(order);
    order.getParticipants().forEach(participant -> {
      System.out.println(participant.getName() + ": R$" + participant.getFinancials().getTotalIndividualExpense());
    });

  }

  private void caculateTotalIndividualOfIncreasesAndDiscounts(Order order) {

    double totalPriceItemsOfOrder = calculateSumTotalItemsOfOrder(order);
    double totalIncreases = calculateTotalIncreasesOfOrder(order);
    double totalDiscounts = calculateTotalDiscountsOfOrder(order);

    order.getParticipants()
        .forEach(participant -> {
          calculateSumPriceTotalOfItems(participant, totalPriceItemsOfOrder);
          calculateTotalIndividualIncrease(participant, totalIncreases);
          calculateTotalIndividualDiscount(participant, totalDiscounts);
          calculateDifferenceIncreaseAndDiscount(participant);
          calculateTotalIndividualExpense(participant);

        });
  }

  private void calculateSumPriceTotalOfItems(Participant participant, double totalPriceItemsOfOrder) {

    ParticipantFinancials financials = participant.getFinancials();
    double sumPriceTotalOfItems = calculateSumPriceTotalOfItems(participant.getItems());
    financials.setSumPriceTotalOfItems(sumPriceTotalOfItems);
    double individualPercentage = sumPriceTotalOfItems / totalPriceItemsOfOrder;
    financials.setIndividualPercentage(individualPercentage);
  }

  private void calculateTotalIndividualIncrease(Participant participant, double totalIncreases) {

    ParticipantFinancials financials = participant.getFinancials();
    double totalIndividualIncrease = financials.getIndividualPercentage() * totalIncreases;
    financials.setTotalIndividualIncrease(totalIndividualIncrease);
  }

  private void calculateTotalIndividualDiscount(Participant participant, double totalDiscounts) {

    ParticipantFinancials financials = participant.getFinancials();
    double totalIndividualDiscount = financials.getIndividualPercentage() * totalDiscounts;
    financials.setTotalIndividualDiscount(totalIndividualDiscount);
  }

  private void calculateDifferenceIncreaseAndDiscount(Participant participant) {

    ParticipantFinancials financials = participant.getFinancials();
    double differenceIncreaseAndDiscount = financials.getTotalIndividualIncrease() - financials.getTotalIndividualDiscount();
    financials.setDifferenceIncreaseAndDiscountIndividual(Math.abs(differenceIncreaseAndDiscount));
  }

  private void calculateTotalIndividualExpense(Participant participant) {

    ParticipantFinancials financials = participant.getFinancials();
    double totalPaidParticipant = financials.getSumPriceTotalOfItems() - financials.getDifferenceIncreaseAndDiscountIndividual();
    double totalIndividualExpense = Math.round(totalPaidParticipant * 100.0) / 100.0;
    financials.setTotalIndividualExpense(totalIndividualExpense);
  }

  private double calculateTotalIncreasesOfOrder(Order order) {

    return order.getIncreases().stream().mapToDouble(Increase::getValue).sum();
  }

  private double calculateTotalDiscountsOfOrder(Order order) {

    return order.getDiscounts().stream().mapToDouble(Discount::getValue).sum();

  }


  private double calculateSumPriceTotalOfItems(List<Item> items) {

    return items.stream()
        .mapToDouble(Item::getValue)
        .sum();
  }


  private double calculateSumTotalItemsOfOrder(Order order) {

    return order.getParticipants().stream()
        .flatMap(participant -> participant.getItems().stream())
        .mapToDouble(Item::getValue)
        .sum();

  }
}
