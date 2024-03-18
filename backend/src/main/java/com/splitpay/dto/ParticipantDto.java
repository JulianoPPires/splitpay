package com.splitpay.dto;

import com.splitpay.model.Item;
import com.splitpay.model.ParticipantFinancials;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDto {

  @NotBlank(message = "O nome do participante nao pode estar branco")
  private String name;
  @NotEmpty(message = "A lista de itens não pode estar vazia")
  private List<Item> items;
  private ParticipantFinancials financials;
}
