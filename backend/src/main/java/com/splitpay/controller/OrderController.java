package com.splitpay.controller;

import com.splitpay.dto.OrderRequestDto;
import com.splitpay.dto.OrderResponseDto;
import com.splitpay.model.Order;
import com.splitpay.service.SplitOrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
  @PostMapping(value ="/split-order", produces = "application/json")
  public ResponseEntity<OrderResponseDto> splitOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {

    Order order = modelMapper.map(orderRequestDto, Order.class);

    return new ResponseEntity<>(splitOrderService.calculateTotalPaidByEachParticipantAndGenerateLinkToPaiment(order), HttpStatus.OK);
  }
}
