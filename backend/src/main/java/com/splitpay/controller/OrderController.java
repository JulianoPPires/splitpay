package com.splitpay.controller;

import com.splitpay.dto.OrderRequestDto;
import com.splitpay.dto.OrderResponseDto;
import com.splitpay.model.Order;
import com.splitpay.service.SplitOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@Tag(name = "Order", description = "Api to carry out the proportional division of expenses between purchasing participants.")
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  private final SplitOrderService splitOrderService;
  private final ModelMapper modelMapper;

  public OrderController(SplitOrderService splitOrderService, ModelMapper modelMapper) {

    this.splitOrderService = splitOrderService;
    this.modelMapper = modelMapper;
  }

  @Operation(
      summary = "Split bill between participants",
      description = "Route to carry out the proportional division of expenses between purchasing participants.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "successful operation"),
      @ApiResponse(responseCode = "400", description = "bad request")
  })
  @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
  @PostMapping(value ="/split-order", produces = "application/json")
  public ResponseEntity<OrderResponseDto> splitOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {

    Order order = modelMapper.map(orderRequestDto, Order.class);

    return new ResponseEntity<>(splitOrderService.calculateTotalPaidByEachParticipantAndGenerateLinkToPayment(order), HttpStatus.OK);
  }
}
