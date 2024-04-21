package com.splitpay.fixture;

import com.splitpay.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderFixture {

  public static Order createOrderEqualsExampleProblem() {

    Order order = new Order();

    List<Participant> participants = createParticipants();


    List<Item> items = createItemsParticipant1();
    List<Item> items2 = createItemsParticipant2();

    participants.get(0).setItems(items);
    participants.get(1).setItems(items2);


    order.setParticipants(participants);

    order.setIncreases(createItemsIncreasesOrder1());
    order.setDiscounts(createDiscountsOrder1());

    return order;
  }

  public static Order createOrderWithDiscountsEqualsZero() {

    Order order = new Order();

    List<Participant> participants = createParticipants();


    List<Item> items = createItemsParticipant1();
    List<Item> items2 = createItemsParticipant2();

    participants.get(0).setItems(items);
    participants.get(1).setItems(items2);


    order.setParticipants(participants);

    order.setIncreases(createItemsIncreasesOrder1());
    order.setDiscounts( createDiscountsEqualsZero());

    return order;
  }

  public static Order createOrderWithIncreasesEqualsZero() {

    Order order = new Order();

    List<Participant> participants = createParticipants();


    List<Item> items = createItemsParticipant1();
    List<Item> items2 = createItemsParticipant2();

    participants.get(0).setItems(items);
    participants.get(1).setItems(items2);


    order.setParticipants(participants);

    order.setIncreases(createIncreasesEqualsZero());
    order.setDiscounts(createDiscountsOrder1());

    return order;
  }

  public static List<Participant> createParticipants() {
    Participant participant1 = new Participant();
    Participant participant2 = new Participant();
    participant1.setName("Nome participante 1");
    participant2.setName("Nome participante 2");

    List<Participant> participants = new ArrayList<>();
    participants.add(participant1);
    participants.add(participant2);

    return participants;
  }

  public static List<Item> createItemsParticipant1() {
    BigDecimal valor = new BigDecimal(40);
    BigDecimal valor2 = new BigDecimal(2);
    Item item1 = new Item("Hamburguer", valor);
    Item item2 = new Item("Sobremesa", valor2);

    List<Item> items = new ArrayList<>();
    items.add(item1);
    items.add(item2);

    return items;
  }

  public static List<Item> createItemsParticipant2() {
    BigDecimal valor = new BigDecimal(8);
    Item item1 = new Item("Sanduíche", valor);

    List<Item> items = new ArrayList<>();
    items.add(item1);

    return items;
  }

  public static List<Operation> createItemsIncreasesOrder1() {
    BigDecimal valor = new BigDecimal(8);
    Operation increase = new Increase("Entrega", valor, false);

    List<Operation> increases = new ArrayList<>();
    increases.add(increase);

    return increases;
  }

  public static List<Operation> createDiscountsEqualsZero() {

    Operation discount = new Discount("Desconto", BigDecimal.ZERO, false);

    List<Operation> discounts = new ArrayList<>();
    discounts.add(discount);

    return discounts;
  }

  public static List<Operation> createIncreasesEqualsZero() {

    Operation increase = new Increase("Frete", BigDecimal.ZERO, false);

    List<Operation> increases = new ArrayList<>();
    increases.add(increase);

    return increases;
  }

  public static List<Operation> createDiscountsOrder1() {
    BigDecimal valor = new BigDecimal(20);
    Operation discount = new Discount("Desconto", valor, false);

    List<Operation> discounts = new ArrayList<>();
    discounts.add(discount);

    return discounts;
  }

}
