package com.splitpay.model;

import com.splitpay.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Participant {

  private String name;

  private List<Item> items;
  private ParticipantFinancials financials;

  public Participant() {
    this.financials = new ParticipantFinancials();
  }
}
