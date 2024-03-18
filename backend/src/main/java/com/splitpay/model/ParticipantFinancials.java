package com.splitpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantFinancials {
  private double totalIndividualExpense;
  private double totalIndividualIncrease;
  private double totalIndividualDiscount;
  private double individualPercentage;
  private double differenceIncreaseAndDiscountIndividual;
  private double sumValueTotalOfItems;
}
