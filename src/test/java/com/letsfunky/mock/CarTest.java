package com.letsfunky.mock;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.Mockito.*;

class CarTest {

    // examine mock
    // `Mock` waits to be called by the SUT(system under test)
    @Test
    void 자동차_시동을_걸면_엔진체크를_한다() {
        // todo: arrange
        //  1. create mock instance of Engine
        //  2. inject Engine mock into Car

        // todo: act
        //  1. start Car's engine

        // todo: assert
        //  1. verify `Engine.selfCheck()` has been invoked `exactly once`

    }

    // examine stub
    // `Stub` gives out data that goes to the SUT
    @Test
    void 엔진에_문제가_있으면_자동차_시동이_실패한다() {
        // todo: arrange
        //  1. create Engine mock for stubbing
        //  2. doThrow IllegalStateException when engine selfCheck() invoked
        //  3. inject Engine stub into Car

        // todo: act & assert
        //  1. assert that IllegalStateException thrown by when Car's engine started
    }
}