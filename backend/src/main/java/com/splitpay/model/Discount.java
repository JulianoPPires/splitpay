package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {

  private String name;
  private double price;
}
