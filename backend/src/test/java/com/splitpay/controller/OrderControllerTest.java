package com.splitpay.controller;

import com.splitpay.dto.OrderRequestDto;
import com.splitpay.dto.OrderResponseDto;
import com.splitpay.fixture.OrderFixture;
import com.splitpay.fixture.OrderResponseDtoFixture;
import com.splitpay.model.Order;
import com.splitpay.service.SplitOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

  @Mock
  private SplitOrderService splitOrderService;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private OrderController orderController;

  @BeforeEach
  public void setUp() {

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testSplitOrder_Success() {
    OrderRequestDto requestDto = new OrderRequestDto();
    Order order = OrderFixture.createOrderEqualsExampleProblem();
    OrderResponseDto responseDto = OrderResponseDtoFixture.createOrderResponse();
    when(modelMapper.map(requestDto, Order.class)).thenReturn(order);
    when(splitOrderService.calculateTotalPaidByEachParticipantAndGenerateLinkToPaiment(order)).thenReturn(responseDto);

    ResponseEntity<OrderResponseDto> responseEntity = orderController.splitOrder(requestDto);

    verify(modelMapper, times(1)).map(requestDto, Order.class);
    verify(splitOrderService, times(1)).calculateTotalPaidByEachParticipantAndGenerateLinkToPaiment(order);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(responseDto, responseEntity.getBody());
  }

}