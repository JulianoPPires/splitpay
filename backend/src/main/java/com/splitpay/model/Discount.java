package com.splitpay.model;

import java.math.BigDecimal;

public class Discount extends Operation {
    public Discount(String name, BigDecimal value, boolean isPercentage) {
        super(name, value, isPercentage);
    }
}
