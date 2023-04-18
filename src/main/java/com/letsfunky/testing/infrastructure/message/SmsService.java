package com.letsfunky.testing.infrastructure.message;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsRequest;
import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final SmsApiClient smsApiClient;

    public SmsService(SmsApiClient smsApiClient) {
        this.smsApiClient = smsApiClient;
    }

    @Async
    public SmsResponse send(String phoneNumber, String message) {
        var response = smsApiClient.send(new SmsRequest(phoneNumber, message));

        if (response.isSuccess()) {
            /*
                complex business logic omitted intentionally
             */

            return response;
        } else {
            throw new RuntimeException("sms send failed");
        }
    }
}
