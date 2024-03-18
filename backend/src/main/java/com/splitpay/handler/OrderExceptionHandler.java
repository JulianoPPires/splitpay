package com.splitpay.handler;

import com.splitpay.handler.exception.RequisicaoInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    if (bindingResult.hasErrors()) {
      FieldError fieldError = bindingResult.getFieldErrors().get(0);
      return ResponseEntity.badRequest().body("Requisição inválida: " + fieldError.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body("Requisição inválida, verifique os dados");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<String> handleJsonMalFormatadoException(HttpMessageNotReadableException ex) {

    return ResponseEntity.badRequest().body("Requisição inválida: Verifique a formatação do Json");
  }


}
