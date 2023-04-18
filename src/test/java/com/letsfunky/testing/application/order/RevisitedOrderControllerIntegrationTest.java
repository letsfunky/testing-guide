package com.letsfunky.testing.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.letsfunky.testing.MockMvcTestBase;
import com.letsfunky.testing.application.order.OrderDto.OrderDetailResponse;
import com.letsfunky.testing.application.order.OrderDto.OrderRequest;
import com.letsfunky.testing.application.order.OrderDto.TestRequest;
import com.letsfunky.testing.application.order.OrderDto.TestResponse;
import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.Order;
import com.letsfunky.testing.domain.order.OrderRepository;
import com.letsfunky.testing.infrastructure.message.SmsApiService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
class RevisitedOrderControllerIntegrationTest extends MockMvcTestBase {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @MockBean
    private SmsApiService dummySmsApiService;

    private final static String baseUrl = "/orders";

    @SneakyThrows
    @Test
    void 주문상세_조회에_실패한다() {
        mockMvc.perform(get(baseUrl + "/12345"))
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is5xxServerError());
    }

    @SneakyThrows
    @Test
    @Disabled
    void 주문상세_조회에_성공한다_no_dbunit() {
        var member = memberRepository.saveAndFlush(new Member("name"));
        var order = orderRepository.saveAndFlush(new Order(member.getId(), "address"));
        // var orderLine = orderLineRepository.saveAndFlush(..)
        // var store = storeRepository.saveAndFlush(..)
        // var inventories = inventoryRepository.saveAndFlush(..)

        mockMvc.perform(get(baseUrl + "/" + order.getId())) // no dangling
            .andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void 주문에_성공한다() {
        // NOTE: dbunit? rider?
        var member = memberRepository.saveAndFlush(new Member("member-name"));
        var orderRequest = new OrderRequest(member.getId(), "goods", 3, "phone-num", "shipping-address");

        var resultActions = mockMvc.perform(
                post(baseUrl + "/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(orderRequest))
            )
            .andDo(print())
            .andExpect(status().isOk());

        var response = toResponse(resultActions, OrderDetailResponse.class);
        assertThat(response.getOrdererId()).isEqualTo(member.getId());
        assertThat(response.getOrdererName()).isEqualTo(member.getName());
        assertThat(response.getShippingAddress()).isEqualTo(orderRequest.getShippingAddress());
    }

    @SneakyThrows
    @Test
    void sut가_다른_thread라_테스트가_실패한다(@Autowired TestRestTemplate testRestTemplate) {
        var member = memberRepository.saveAndFlush(new Member("member-name"));
        var orderRequest = new OrderRequest(member.getId(), "goods", 3, "phone-num", "shipping-address");

        ResponseEntity<OrderDetailResponse> responseEntity =
            testRestTemplate.postForEntity(baseUrl + "/", orderRequest, OrderDetailResponse.class);

        var response = responseEntity.getBody();
        assertThat(response.getOrdererId()).isEqualTo(member.getId());
        assertThat(response.getOrdererName()).isEqualTo(member.getName());
        assertThat(response.getShippingAddress()).isEqualTo(orderRequest.getShippingAddress());
    }

    @SneakyThrows
    @Test
    void primitive_boolean의_is_prefix가_response에서_사라진다() {
        var givenParam = false;
        var resultActions = mockMvc.perform(
                post(baseUrl + "/dto-test")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(new TestRequest(givenParam, "dummy-string")))
            )
            .andDo(print())
            .andExpect(status().isOk());

        var response = toResponse(resultActions, TestResponse.class);
        assertThat(response.isSuccess()).isEqualTo(!givenParam); // NOTE: fails due to ser/deser behavior
        assertThat(response.getIsActive()).isEqualTo(!givenParam);

        // NOTE: add if needed
        resultActions
            .andExpect(MockMvcResultMatchers.jsonPath("$.isSuccessful", Is.is(givenParam)));
    }
}
