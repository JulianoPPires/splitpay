package com.splitpay.service;

import com.splitpay.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitOrderService {


  public void calculateTotalPaidByEachParticipant(Order order) {

    //passo2
    //calcula orcentagem do valor total correspondente aos itens de cada pessoa
    //Para mim: (40 + 2) / 50 * 100 = 84%
    //Para meu amigo: 8 / 50 * 100 = 16%

    double totalItemsOfOrder = calculateSumTotalItemsOfOrder(order);

    order.getParticipants().stream()
        .forEach(participant -> {
          participant.getFinancials().setSumPriceTotalOfItems(calculateSumPriceTotalOfItems(participant.getItems()));
          participant.getFinancials().setIndividualPercentage((participant.getFinancials().getSumPriceTotalOfItems() / totalItemsOfOrder * 100));
        });

      /* passo3:
    Aplicação da porcentagem para a taxa de entrega e desconto:
    Taxa de entrega: R$8,00
    Para mim: 84% de R$8,00 = R$6,72
    Para meu amigo: 16% de R$8,00 = R$1,28
    Desconto: R$20,00
    Para mim: 84% de R$20,00 = R$16,80
    Para meu amigo: 16% de R$20,00 = R$3,20*/

    double totalIncreases = calculateTotalIncreasesOfOrder(order);
    double totalDiscounts = calculateTotalDiscountsOfOrder(order);

    order.getParticipants().stream()
        .forEach(participant -> {
          participant.getFinancials().setTotalAmountIncrease((participant.getFinancials().getIndividualPercentage() / 100) * totalIncreases);
          participant.getFinancials().setTotalAmountDiscount((participant.getFinancials().getIndividualPercentage() / 100) * totalDiscounts);
        });


      /* passo4:
      Subtração para encontrar a quantia a ser paga por cada pessoa:
      Para mim: R$6,72 (entrega) - R$16,80 (desconto) = -R$10,08
      Para meu amigo: R$1,28 (entrega) - R$3,20 (desconto) = -R$1,92*/

    order.getParticipants().stream()
        .forEach(participant -> {
          double amountToBePaid = participant.getFinancials().getTotalAmountIncrease() - participant.getFinancials().getTotalAmountDiscount();
          participant.getFinancials().setAmountsToBePaid(Math.abs(amountToBePaid));
        });

          /* passo5:
      Calcular quantia a ser paga por cada pessoa:
      Para mim:  R$31,92 (R$42,00 - R$10,08)
      Para mew amigo: R$6,08 (R$8,00 - R$1,92)*/

    order.getParticipants().stream()
        .forEach(participant -> {
          participant.getFinancials().setIndividualExpense((participant.getFinancials().getSumPriceTotalOfItems() - participant.getFinancials().getAmountsToBePaid()));
        });

    order.getParticipants().forEach(participant -> {
      System.out.println(participant.getName() + ": R$" + participant.getFinancials().getIndividualExpense());
    });

  }

  private double calculateTotalIncreasesOfOrder(Order order) {

    return order.getIncreases().stream().mapToDouble(Increase::getPrice).sum();
  }

  private double calculateTotalDiscountsOfOrder(Order order) {

    return order.getDiscounts().stream().mapToDouble(Discount::getPrice).sum();

  }


  private double calculateSumPriceTotalOfItems(List<Item> items) {

    return items.stream()
        .mapToDouble(Item::getPrice)
        .sum();
  }


  private double calculateSumTotalItemsOfOrder(Order order) {

    return order.getParticipants().stream()
        .flatMap(participant -> participant.getItems().stream())
        .mapToDouble(Item::getPrice)
        .sum();

  }
}
