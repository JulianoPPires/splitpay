package com.splitpay.service;

import com.splitpay.model.Discount;
import com.splitpay.model.Increase;
import com.splitpay.model.Item;
import com.splitpay.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitOrderService {


  public void calculateTotalToBePaidByEachParticipant(Order order) {


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

    return 0;
  }
}
