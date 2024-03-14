package com.splitpay.dto;

import com.splitpay.model.ParticipantFinancials;

import lombok.Data;

@Data
public class ParticipantDto {
  private String name;
  private ParticipantFinancials financials;
}
