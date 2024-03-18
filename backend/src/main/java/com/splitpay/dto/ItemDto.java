package com.splitpay.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemDto {

  @NotBlank(message = "O nome do item nao pode estar branco")
  private String name;

  @NotBlank(message = "O valor do item não pode estar em branco")
  private double value;
}
