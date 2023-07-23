package com.letsfunky.testingstyle;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

// communication-based test
class RevisitedGreetingServiceTest {

    @Test
    void 사용자를_반긴다() {
        // todo: arrange
        //  1. create mock instance of EmailGateway
        //  2. inject EmailGateway mock into GreetingService
        var mockEmailGateway = mock(GreetingService.EmailGateway.class);
        var sut = new GreetingService(mockEmailGateway);

        // todo: act
        //  1. greet user
        sut.greetUser("vegeta");

        // todo: assert
        //  1. verify `emailGateway.sendGreetings()` has been invoked `exactly once`
        verify(mockEmailGateway, times(1)).sendGreetings();
    }
}
