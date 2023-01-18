package com.letsfunky.testing.infrastructure.message;

import java.time.LocalDateTime;
import lombok.Value;

public class SmsApiDto {

    @Value
    public static class SmsRequest {
        String phoneNumber;
        String content;
    }

    @Value
    public static class SmsResponse {
        boolean success;
        // metadata
        String resultMessage;
        String statusCode;
        String mobileOperator;
        String requestedName;
        String requestedPhoneNumber;
        String content;
        LocalDateTime sendRequestedAt;
        LocalDateTime sendCompletedAt;
    }
}
