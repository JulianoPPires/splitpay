package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount {

  private String name;
  private Double value;
  private boolean isPercentage;

}
