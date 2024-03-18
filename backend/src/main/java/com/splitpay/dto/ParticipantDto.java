package com.splitpay.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantDto {

  @NotBlank(message = "O nome do participante nao pode estar branco")
  private String name;

  @Valid
  @NotNull(message = "A lista de itens do participante não pode estar vazia")
  private List<ItemDto> items;

}
