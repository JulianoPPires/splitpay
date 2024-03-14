package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipantFinancials {
  private double individualExpense;
  private double totalAmountIncrease;
  private double totalAmountDiscount;
  private double individualPercentage;
  private double amountsToBePaid;
  private double sumPriceTotalOfItems;
}
