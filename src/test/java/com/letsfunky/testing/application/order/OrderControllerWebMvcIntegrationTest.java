package com.letsfunky.testing.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfunky.testing.application.order.OrderDto.OrderDetailResponse;
import com.letsfunky.testing.application.order.OrderDto.OrderRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(OrderController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String baseUrl = "/orders";

    @SneakyThrows
    @Test
    public void service_layer를_mock하지_않아_테스트가_실패한다() {
        var orderRequest = new OrderRequest(1L, "goods", 3, "phone-num", "shipping-address");

        var resultActions = mockMvc.perform(
                post(baseUrl + "/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(orderRequest))
            )
            .andDo(print())
            .andExpect(status().isOk());

        var response = toResponse(resultActions, OrderDetailResponse.class);
        assertThat(response.getOrdererId()).isEqualTo(1L);
        assertThat(response.getShippingAddress()).isEqualTo(orderRequest.getShippingAddress());
    }

    @SneakyThrows
    private <T> String toJson(T obj) {
        return objectMapper.writeValueAsString(obj);
    }

    @SneakyThrows
    private <T> T toResponse(ResultActions resultActions, Class<T> clazz) {
        return objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), clazz);
    }
}
