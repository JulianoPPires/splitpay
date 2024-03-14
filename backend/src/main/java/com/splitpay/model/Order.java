package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private List<OrderItem> orderItems;

  private List<Increase> increases;
  private List<Discount> discounts;
}
