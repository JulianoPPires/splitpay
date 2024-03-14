package com.splitpay.controller;

import com.splitpay.dto.OrderDto;
import com.splitpay.model.Order;
import com.splitpay.service.SplitOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  private final SplitOrderService splitOrderService;
  private final ModelMapper modelMapper;

  public OrderController(SplitOrderService splitOrderService, ModelMapper modelMapper) {

    this.splitOrderService = splitOrderService;
    this.modelMapper = modelMapper;
  }

  @PostMapping("/splitOrder")
  public ResponseEntity<Object> splitOrder(@RequestBody OrderDto orderDto) {

    Order order = modelMapper.map(orderDto, Order.class);

    splitOrderService.calculateTotalToBePaidByEachParticipant(order);

    return new ResponseEntity<>("Divisão do pedido realizada com sucesso", HttpStatus.OK);
  }
}
