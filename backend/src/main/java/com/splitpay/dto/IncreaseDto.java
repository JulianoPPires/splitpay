package com.splitpay.dto;

import lombok.Data;

@Data
public class IncreaseDto {
  private String name;
  private double value;
  private boolean isPercentage;
}
