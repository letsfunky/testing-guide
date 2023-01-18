package com.letsfunky.testing.infrastructure.message;

import com.letsfunky.testing.infrastructure.message.SmsApiDto.SmsResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SmsApiDtoBuilder {

    public static SmsResponse generateResponse(boolean success) {
        return new SmsResponse(
            success,
            "resultMessage",
            "statusCode",
            "mobileOperator",
            "requestedName",
            "requestedPhoneNumber",
            "content",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    public static SmsResponse generateResponse(boolean success, int index) {
        return new SmsResponse(
            success,
            "resultMessage",
            "statusCode",
            "mobileOperator",
            "requestedName",
            "requestedPhoneNumber-" + index,
            "content",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    public static List<SmsResponse> generateResponses(boolean success, int count) {
        return IntStream.rangeClosed(0, count)
            .mapToObj(i -> generateResponse(success, i))
            .collect(Collectors.toUnmodifiableList());
    }
}
