package com.letsfunky.testing.infrastructure.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsRequest;
import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SmsApiServiceTest {

    private SmsApiClient smsApiClient;
    private SmsApiService sut;

    @BeforeEach
    void init() {
        smsApiClient = mock(SmsApiClient.class);
        sut = new SmsApiService(smsApiClient);
    }

    @Test
    void sms발송이_성공한다() {
        var phoneNumber = "phone-number";
        var smsMessage = "sms-message";
        when(smsApiClient.send(new SmsRequest(phoneNumber, smsMessage))).thenReturn(
            new SmsResponse(
                true,
                "resultMessage",
                "statusCode",
                "mobileOperator",
                "requestedName",
                "requestedPhoneNumber",
                "content",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        );

        var response = sut.send(phoneNumber, smsMessage);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getStatusCode()).isEqualTo("statusCode");
        // and so on..
    }

    @Test
    void sms발송이_실패한다() {
        var phoneNumber = "phone-number";
        var smsMessage = "sms-message";
        when(smsApiClient.send(new SmsRequest(phoneNumber, smsMessage))).thenReturn(
            new SmsResponse(
                false,
                "resultMessage",
                "statusCode",
                "mobileOperator",
                "requestedName",
                "requestedPhoneNumber",
                "content",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        );

        assertThatThrownBy(() -> sut.send(phoneNumber, smsMessage))
            .isExactlyInstanceOf(RuntimeException.class)
            .hasMessage("sms send failed");
    }
}
