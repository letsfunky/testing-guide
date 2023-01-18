package com.letsfunky.testing.infrastructure.message;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsRequest;
import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsResponse;

public class SmsApiService {

    private final SmsApiClient smsApiClient;

    public SmsApiService(SmsApiClient smsApiClient) {
        this.smsApiClient = smsApiClient;
    }

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
