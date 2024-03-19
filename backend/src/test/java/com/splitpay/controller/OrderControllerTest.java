package com.splitpay.controller;

import com.splitpay.fixture.OrderControllerFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {


  @Autowired
  MockMvc mockMvc;

  @Test
  public void testSplitOrder_Success_CompleteJson() throws Exception {
    this.mockMvc.perform(post("/api/v1/order/split-order")
        .contentType(MediaType.APPLICATION_JSON)
        .content(OrderControllerFixture.jsonExampleComplete())
        .accept(MediaType.APPLICATION_JSON)
    )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(OrderControllerFixture.jsonResponseExample1()));
  }

  @Test
  public void testSplitOrder_Success_Without_Increases() throws Exception {
    this.mockMvc.perform(post("/api/v1/order/split-order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(OrderControllerFixture.jsonExampleWithoutIncreses())
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(OrderControllerFixture.jsonResponseWithoutIncreases()));
  }

  @Test
  public void testSplitOrder_Success_Without_Discounts() throws Exception {
    this.mockMvc.perform(post("/api/v1/order/split-order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(OrderControllerFixture.jsonExampleWithoutDiscounts())
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(OrderControllerFixture.jsonResponseWithoutDiscounts()));
  }

  @Test
  public void testSplitOrder_Error_Negative_Value() throws Exception {
    this.mockMvc.perform(post("/api/v1/order/split-order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(OrderControllerFixture.jsonExampleWithoutNeagtiveValue())
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void testSplitOrder_Have_1_Participant_To_divide() throws Exception {
    this.mockMvc.perform(post("/api/v1/order/split-order")
            .contentType(MediaType.APPLICATION_JSON)
            .content(OrderControllerFixture.jsonExampleWith1Participant())
            .accept(MediaType.APPLICATION_JSON)
        )
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }

}