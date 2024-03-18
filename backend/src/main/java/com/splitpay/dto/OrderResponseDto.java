package com.splitpay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class OrderResponseDto {

  private Map<String, String> paymentLinks;


}
