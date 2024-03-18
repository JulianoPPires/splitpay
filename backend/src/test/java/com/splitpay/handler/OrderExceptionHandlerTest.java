package com.splitpay.handler;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderExceptionHandlerTest {

  @Test
  public void testHandleMethodArgumentNotValid() {
    OrderExceptionHandler handler = new OrderExceptionHandler();

    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

    ResponseEntity<Object> responseEntity = handler.handleMethodArgumentNotValid(ex);

    assertEquals(400, responseEntity.getStatusCodeValue());

    assertEquals("Requisição inválida - Verifique os dados enviados", responseEntity.getBody());
  }

  @Test
  public void testHandleJsonMalFormatadoException() {
    OrderExceptionHandler handler = new OrderExceptionHandler();
    HttpMessageNotReadableException ex = new HttpMessageNotReadableException("Message");

    ResponseEntity<String> responseEntity = handler.handleJsonMalFormatadoException(ex);

    assertEquals(400, responseEntity.getStatusCodeValue());
    assertEquals("Requisição inválida - Verifique a formatação do JsonMessage", responseEntity.getBody());
  }
}
