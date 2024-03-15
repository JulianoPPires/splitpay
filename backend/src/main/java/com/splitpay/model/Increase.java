package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Increase {

  private String name;
  private double value;
  private boolean isPercentage;
}