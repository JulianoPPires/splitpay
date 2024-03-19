package com.splitpay.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncreaseDto {

  @NotBlank(message = "O nome do incremento não pode estar em branco")
  private String name;

  @NotNull
  @Min(value = 0, message = "O valor do incremento deve ser maior ou igual a 0")
  private Double value;

  @NotNull
  @Schema(example = "false")
  private Boolean isPercentage;
}
