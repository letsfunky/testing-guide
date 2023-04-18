package com.letsfunky.testing.application.order;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.infrastructure.message.SmsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private SmsService dummySmsService;

    private final static String baseUrl = "/orders";

    @SneakyThrows
    @Test
    void 주문상세_조회에_실패한다() {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/12345"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @SneakyThrows
    @Test
    @Disabled
    @DatabaseSetup(connection = "dbUnitDatabaseConnection", value = {
        "classpath:dbunit-data/member-active.xml", // member-inactive.xml ...
        "classpath:dbunit-data/order-dummy.xml", // order-dummy-15.xml ...
        "classpath:dbunit-data/order-line-dummy.xml",
        "classpath:dbunit-data/store-dummy.xml",
        "classpath:dbunit-data/inventory-dummy.xml",
    })
    void 주문상세_조회에_성공한다_dbunit() {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/12345"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void 주문에_성공한다() {
        // NOTE: dbunit? rider?
        var member = memberRepository.saveAndFlush(new Member("member-name"));

        mockMvc.perform(
            MockMvcRequestBuilders.post(baseUrl + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                    + "  \"ordererId\": 1," // dangling reference
                    + "  \"goods\": \"goods\","
                    + "  \"count\": 3,"
                    + "  \"phoneNumber\": \"phone-num\","
                    + "  \"shippingAddress\": \"shipping-addr\""
                    + "}"
                )
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.ordererId", is(member.getId().intValue())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ordererName", is(member.getName())))
            .andExpect(MockMvcResultMatchers.jsonPath("$.shippingAddress", is("shipping-addr")));
    }
}
