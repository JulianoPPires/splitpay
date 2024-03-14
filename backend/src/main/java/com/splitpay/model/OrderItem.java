package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {

  private Participant participant;
  private Item item;
}
