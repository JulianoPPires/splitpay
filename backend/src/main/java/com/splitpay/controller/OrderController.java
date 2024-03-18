package com.splitpay.controller;

import com.splitpay.dto.OrderDto;
import com.splitpay.model.Order;
import com.splitpay.service.SplitOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  private final SplitOrderService splitOrderService;
  private final ModelMapper modelMapper;

  public OrderController(SplitOrderService splitOrderService, ModelMapper modelMapper) {

    this.splitOrderService = splitOrderService;
    this.modelMapper = modelMapper;
  }

  @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
  @PostMapping(value ="/splitorder", produces = "application/json")
  public ResponseEntity<Object> splitOrder(@RequestBody @Valid OrderDto orderDto) {

    Order order = modelMapper.map(orderDto, Order.class);

    splitOrderService.calculateTotalPaidByEachParticipant(order);

    return new ResponseEntity<>("{}", HttpStatus.OK);
  }
}
