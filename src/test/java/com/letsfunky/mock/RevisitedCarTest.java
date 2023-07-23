package com.letsfunky.mock;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class RevisitedCarTest {

    // examine mock
    // `Mock` waits to be called by the SUT(system under test)
    @Test
    void 자동차_시동을_걸면_엔진체크를_한다() {
        // todo: arrange
        //  1. create mock instance of Engine
        //  2. inject Engine mock into Car
        var mockEngine = mock(Engine.class);
        var car = new Car(mockEngine);

        // todo: act
        //  1. start Car's engine
        car.startEngine();

        // todo: assert
        //  1. verify `Engine.selfCheck()` has been invoked `exactly once`
        verify(mockEngine, times(1)).selfCheck();
    }

    // examine stub
    // `Stub` gives out data that goes to the SUT
    @Test
    void 엔진에_문제가_있으면_자동차_시동이_실패한다() {
        // todo: arrange
        //  1. create Engine mock for stubbing
        //  2. doThrow IllegalStateException when engine selfCheck() invoked
        //  3. inject Engine stub into Car
        var engine = mock(Engine.class);
        var errorMessage = "engine failed";
        // when(engine.selfCheck()).thenThrow(new IllegalStateException(errorMessage));
        doThrow(new IllegalStateException(errorMessage)).when(engine).selfCheck();
        var car = new Car(engine);

        // todo: act & assert
        //  1. assert that IllegalStateException thrown by when Car's engine started
        assertThatThrownBy(car::startEngine)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage(errorMessage);
    }
}