package com.letsfunky.mock;



import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CarTest {

    // examine mock
    // `Mock` waits to be called by the SUT(system under test)
    @Test
    void 자동차_시동을_걸면_엔진체크를_한다() {
        // todo: act
        //  1. create mock instance of Engine
        //  2. inject Engine mock into Car

        // todo: act
        //  1. start Car's engine

        // todo: assert
        //  1. verify `Engine.selfCheck()` has been invoked `exactly once`

    }
}