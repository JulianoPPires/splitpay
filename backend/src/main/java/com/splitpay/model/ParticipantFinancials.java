package com.splitpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ParticipantFinancials {
  private BigDecimal totalIndividualExpense;
  private BigDecimal totalIndividualIncrease;
  private BigDecimal totalIndividualDiscount;
  private BigDecimal individualPercentage;
  private BigDecimal differenceIncreaseAndDiscountIndividual;
  private BigDecimal sumValueTotalOfItems;
}
