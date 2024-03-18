package com.splitpay.dto;

import com.splitpay.model.Discount;
import com.splitpay.model.Increase;

import java.util.List;

import com.splitpay.model.Participant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderRequestDto {
  @Valid
  @NotNull
  @Size(min = 2, message = "A lista deve conter no mínimo dois participantes para a divisao")
  private List<Participant> participants;

  @Valid
  private List<IncreaseDto> increases;

  @Valid
  private List<DiscountDto> discounts;
}
