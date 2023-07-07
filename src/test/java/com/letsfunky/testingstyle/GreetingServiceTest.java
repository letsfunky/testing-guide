package com.letsfunky.testingstyle;

import com.letsfunky.testingstyle.GreetingService.EmailGateway;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

// communication-based test
class GreetingServiceTest {

    @Test
    void 사용자를_반긴다() {
        // arrange
        var emailGateway = mock(EmailGateway.class);
        var sut = new GreetingService(emailGateway);

        // act
        sut.greetUser("user1");

        // assert
        // todo: verify `emailGateway.sendGreetings()` has been invoked `exactly once`
    }

}