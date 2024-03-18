package com.splitpay.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDto {

  @NotNull
  @Size(min = 2, message = "A lista deve conter no mínimo dois participantes para a divisao")
  @Valid
  private List<ParticipantDto> participants;

  @Valid
  private List<IncreaseDto> increases;

  @Valid
  private List<DiscountDto> discounts;
}
