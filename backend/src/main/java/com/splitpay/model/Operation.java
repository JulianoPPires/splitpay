package com.splitpay.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private String name;
    private BigDecimal value;
    private boolean isPercentage;

    public BigDecimal getValue() {

        if (isPercentage) {
            BigDecimal hundred = new BigDecimal("100");
            return value.divide(hundred); // Divide o valor pela constante '100'
        } else {
            return value; // Se não for porcentagem, retorna o valor original
        }
    }

}
