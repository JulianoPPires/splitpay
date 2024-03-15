package com.splitpay.service;

import com.splitpay.model.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class SplitOrderService {


  public void calculateTotalPaidByEachParticipant(Order order) {

    caculateTotalIndividualOfIncreasesAndDiscounts(order);

    order.getParticipants().forEach(participant -> {
      System.out.println(participant.getName() + ": R$" + participant.getFinancials().getTotalIndividualExpense());
    });

  }

  private void caculateTotalIndividualOfIncreasesAndDiscounts(Order order) {
    double totalPriceItemsOfOrder = calculateSumTotalItemsOfOrder(order);
    double totalIncreases = calculateTotalIncreasesOfOrder(order);
    double totalDiscounts = calculateTotalDiscountsOfOrder(order);

    order.getParticipants().stream()
        .forEach(participant -> {
          participant.getFinancials().setSumPriceTotalOfItems(calculateSumPriceTotalOfItems(participant.getItems()));
          participant.getFinancials().setIndividualPercentage((participant.getFinancials().getSumPriceTotalOfItems() / totalPriceItemsOfOrder ));
          participant.getFinancials().setTotalIndividualIncrease((participant.getFinancials().getIndividualPercentage()) * totalIncreases);
          participant.getFinancials().setTotalIndividualDiscount((participant.getFinancials().getIndividualPercentage()) * totalDiscounts);
          double differenceIncreaseAndDiscount = participant.getFinancials().getTotalIndividualIncrease() - participant.getFinancials().getTotalIndividualDiscount();
          participant.getFinancials().setAmountsToBePaid(Math.abs(differenceIncreaseAndDiscount));
          double totalPaidParticipant = (participant.getFinancials().getSumPriceTotalOfItems() - participant.getFinancials().getAmountsToBePaid());
          double roundedNumber = Math.round(totalPaidParticipant * 100.0) / 100.0;
          participant.getFinancials().setTotalIndividualExpense(roundedNumber);
        });
  }

  private double calculateTotalIncreasesOfOrder(Order order) {

    return order.getIncreases().stream().mapToDouble(Increase::getPrice).sum();
  }

  private double calculateTotalDiscountsOfOrder(Order order) {

    return order.getDiscounts().stream().mapToDouble(Discount::getPrice).sum();

  }


  private double calculateSumPriceTotalOfItems(List<Item> items) {

    return items.stream()
        .mapToDouble(Item::getPrice)
        .sum();
  }


  private double calculateSumTotalItemsOfOrder(Order order) {

    return order.getParticipants().stream()
        .flatMap(participant -> participant.getItems().stream())
        .mapToDouble(Item::getPrice)
        .sum();

  }
}
