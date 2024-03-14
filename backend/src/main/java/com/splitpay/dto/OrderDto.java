package com.splitpay.dto;

import com.splitpay.model.Discount;
import com.splitpay.model.Increase;
import com.splitpay.model.OrderItem;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
  private List<OrderItem> orderItems;

  private List<Increase> increases;
  private List<Discount> discounts;
}
