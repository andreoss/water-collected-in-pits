package org.sdf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link WaterPools}.
 */
final class WaterPoolsTest {

    @Test
    void originalExample() {
        Assertions.assertEquals(
            WaterPools.calculateWaterAmount(
                new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}
            ),
            9L
        );
    }
}