package com.splitpay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class DiscountDto {

  @NotBlank(message = "O nome do incremento não pode estar em branco")
  private String name;

  @NotNull(message = "O valor do desconto não pode ser nulo")
  @Min(value = 0, message = "O valor do disconto deve ser maior ou igual a 0")
  private Double value = 0.0;

  @NotNull
  @Schema(example = "false")
  private Boolean isPercentage;

}
