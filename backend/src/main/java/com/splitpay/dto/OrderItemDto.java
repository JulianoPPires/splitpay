package com.splitpay.dto;

import com.splitpay.model.Item;
import com.splitpay.model.Participant;

import lombok.Data;

@Data
public class OrderItemDto {
  private Participant participant;
  private Item item;
}
