package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Participant {
  private String name;
  private ParticipantFinancials financials;
}
