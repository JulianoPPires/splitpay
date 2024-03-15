package com.splitpay.dto;

import com.splitpay.model.Discount;
import com.splitpay.model.Increase;

import java.util.List;

import com.splitpay.model.Participant;
import lombok.Data;

@Data
public class OrderDto {
  private List<Participant> participants;

  private List<Increase> increases;
  private List<Discount> discounts;
}
