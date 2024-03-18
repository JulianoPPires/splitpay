package com.splitpay.fixture;

import com.splitpay.model.*;

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

    List<Participant> participants = new ArrayList<>();
    participants.add(participant1);
    participants.add(participant2);

    return participants;
  }

  public static List<Item> createItemsParticipant1() {

    Item item1 = new Item("Hamburguer", 40.00);
    Item item2 = new Item("Sobremesa", 2.00);

    List<Item> items = new ArrayList<>();
    items.add(item1);
    items.add(item2);

    return items;
  }

  public static List<Item> createItemsParticipant2() {

    Item item1 = new Item("Sanduíche", 8.00);

    List<Item> items = new ArrayList<>();
    items.add(item1);

    return items;
  }

  public static List<Increase> createItemsIncreasesOrder1() {

    Increase increase = new Increase("Entrega", 8.00, false);

    List<Increase> increases = new ArrayList<>();
    increases.add(increase);

    return increases;
  }

  public static List<Discount> createDiscountsEqualsZero() {

    Discount discount = new Discount("Desconto", 0.0, false);

    List<Discount> discounts = new ArrayList<>();
    discounts.add(discount);

    return discounts;
  }

  public static List<Increase> createIncreasesEqualsZero() {

    Increase increase = new Increase("Frete", 0.0, false);

    List<Increase> increases = new ArrayList<>();
    increases.add(increase);

    return increases;
  }

  public static List<Discount> createDiscountsOrder1() {

    Discount discount = new Discount("Desconto", 20.00, false);

    List<Discount> discounts = new ArrayList<>();
    discounts.add(discount);

    return discounts;
  }

}
