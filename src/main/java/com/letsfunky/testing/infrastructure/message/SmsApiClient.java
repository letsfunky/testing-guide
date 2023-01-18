package com.letsfunky.testing.infrastructure.message;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sms-api")
public interface SmsApiClient {

    @PostMapping("/send")
    SmsApiDto.SmsResponse send(@RequestBody SmsApiDto.SmsRequest request);
}
