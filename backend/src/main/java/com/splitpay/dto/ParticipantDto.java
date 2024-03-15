package com.splitpay.dto;

import com.splitpay.model.Item;
import com.splitpay.model.ParticipantFinancials;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDto {

  private String name;
  private List<Item> items;
  private ParticipantFinancials financials;
}
