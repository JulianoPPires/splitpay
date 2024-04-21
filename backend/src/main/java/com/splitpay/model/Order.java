package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private List<Participant> participants;

  private List<Operation> increases;
  private List<Operation> discounts;

  private double totalPriceOrder;

  private double totalPriceIncreases;

  private double totalPriceDiscounts;
}
