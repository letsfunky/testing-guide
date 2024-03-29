package com.letsfunky.testing.infrastructure.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsRequest;
import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SmsServiceTest {

    private SmsApiClient smsApiClient;
    private SmsService sut;

    @BeforeEach
    void init() {
        smsApiClient = mock(SmsApiClient.class);
        sut = new SmsService(smsApiClient);
    }

    @Test
    void sms발송이_성공한다() {
        var phoneNumber = "phone-number";
        var smsMessage = "sms-message";
        var request = new SmsRequest(phoneNumber, smsMessage);
        when(smsApiClient.send(request)).thenReturn(
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

        verify(smsApiClient, times(1)).send(request); // do not verify stub
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void sms발송이_실패한다() {
        var phoneNumber = "phone-number";
        var smsMessage = "sms-message";
        var request = new SmsRequest(phoneNumber, smsMessage);
        when(smsApiClient.send(request)).thenReturn(
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
        verify(smsApiClient, times(1)).send(request); // do not verify stub
    }
}
