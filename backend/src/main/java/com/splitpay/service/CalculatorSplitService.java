package com.splitpay.service;

import com.splitpay.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculatorSplitService {

    protected Order calculateTotalIndividualOfIncreasesAndDiscounts(Order order) {
        BigDecimal totalValueItemsOfOrder = calculateSumTotalItemsOfOrder(order);
        BigDecimal totalIncreases = calculateTotalIncreasesOfOrder(order);
        BigDecimal totalDiscounts = calculateTotalDiscountsOfOrder(order);

        order.getParticipants().forEach(participant -> {
            ParticipantFinancials financials = participant.getFinancials();
            financials.setSumValueTotalOfItems(calculateSumTotalValueItems(participant.getItems()));
            calculateIndividualPercentageOfTotal(financials, totalValueItemsOfOrder);
            calculateTotalIndividualIncrease(financials, totalIncreases);
            calculateTotalIndividualDiscount(financials, totalDiscounts);
            calculateDifferenceBetweenOperations(financials);
            calculateTotalIndividualExpense(financials);
        });

        return order;
    }


    private void calculateDifferenceBetweenOperations(ParticipantFinancials financials) {
        BigDecimal differenceIncreaseAndDiscount = financials.getTotalIndividualIncrease().subtract(financials.getTotalIndividualDiscount());
        financials.setDifferenceIncreaseAndDiscountIndividual(differenceIncreaseAndDiscount);
    }

    private void calculateTotalIndividualExpense(ParticipantFinancials financials) {
        BigDecimal totalPaidParticipant = (financials.getSumValueTotalOfItems().add(financials.getTotalIndividualIncrease())).subtract(financials.getTotalIndividualDiscount());
        financials.setTotalIndividualExpense(totalPaidParticipant);
    }


    private void calculateTotalIndividualDiscount(ParticipantFinancials financials, BigDecimal totalDiscounts) {
        financials.setTotalIndividualDiscount(calculateTotalIndividualOperations(financials, totalDiscounts));
    }

    private void calculateTotalIndividualIncrease(ParticipantFinancials financials, BigDecimal totalIncreases) {
        financials.setTotalIndividualIncrease(calculateTotalIndividualOperations(financials, totalIncreases));
    }

    private BigDecimal calculateTotalIndividualOperations(ParticipantFinancials financials, BigDecimal totalOperations) {
        BigDecimal totalIndividualOperation = financials.getIndividualPercentage().multiply(BigDecimal.ONE.add(totalOperations));
        totalIndividualOperation = totalIndividualOperation.setScale(2, RoundingMode.HALF_UP);
        return totalIndividualOperation;

    }

    private void calculateIndividualPercentageOfTotal(ParticipantFinancials financials, BigDecimal totalValueItemsOfOrder) {
        BigDecimal individualPercentage = financials.getSumValueTotalOfItems().divide(totalValueItemsOfOrder, 2, RoundingMode.HALF_UP);
        financials.setIndividualPercentage(individualPercentage);
    }

    protected BigDecimal calculateTotalIncreasesOfOrder(Order order) {
        return calculateTotalOperationsOfOrder(order, order.getIncreases());
    }

    protected BigDecimal calculateTotalDiscountsOfOrder(Order order) {
        return calculateTotalOperationsOfOrder(order, order.getDiscounts());
    }

    protected BigDecimal calculateTotalOperationsOfOrder(Order order, List<Operation> operations) {
        BigDecimal totalOperations = BigDecimal.ZERO;

        if (operations != null) {
            for (Operation operation : operations) {
                BigDecimal adjustmentValue = operation.getValue();
                if (operation.isPercentage()) {
                    BigDecimal totalItems = calculateSumTotalItemsOfOrder(order);
                    totalOperations = totalOperations.add(adjustmentValue.multiply(totalItems));
                } else {
                    totalOperations = totalOperations.add(adjustmentValue);
                }
            }
        }

        return totalOperations;
    }


    protected BigDecimal calculateSumTotalValueItems(List<Item> items) {
        return items.stream()
                .map(Item::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    protected BigDecimal calculateSumTotalItemsOfOrder(Order order) {
        return order.getParticipants().stream()
                .flatMap(participant -> participant.getItems().stream())
                .map(Item::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        /*
        for (Participant participant : order.getParticipants()) {
            for (Item item : participant.getItems()) {
                total = total.add(item.getValue());
            }
        }*/
    }
}
