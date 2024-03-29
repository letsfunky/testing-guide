package com.letsfunky.testing.infrastructure.message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedSmsServiceTest {

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
        when(smsApiClient.send(new SmsRequest(phoneNumber, smsMessage)))
            .thenReturn(RevisitedSmsApiDtoBuilder.generateResponse(true));

        var response = sut.send(phoneNumber, smsMessage);

        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void sms발송이_실패한다() {
        var phoneNumber = "phone-number";
        var smsMessage = "sms-message";
        when(smsApiClient.send(new SmsRequest(phoneNumber, smsMessage)))
            .thenReturn(RevisitedSmsApiDtoBuilder.generateResponse(false));

        assertThatThrownBy(() -> sut.send(phoneNumber, smsMessage))
            .isExactlyInstanceOf(RuntimeException.class)
            .hasMessage("sms send failed");
    }
}
