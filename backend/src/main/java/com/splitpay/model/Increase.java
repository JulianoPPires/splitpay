package com.splitpay.model;

import java.math.BigDecimal;

public class Increase extends Operation{
    public Increase(String name, BigDecimal value, boolean isPercentage) {
        super(name, value, isPercentage);
    }
}