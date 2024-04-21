package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private List<Participant> participants;

    private List<Operation> increases;
    private List<Operation> discounts;

    private BigDecimal totalPriceOrder;

    private BigDecimal totalPriceIncreases;

    private BigDecimal totalPriceDiscounts;
}
