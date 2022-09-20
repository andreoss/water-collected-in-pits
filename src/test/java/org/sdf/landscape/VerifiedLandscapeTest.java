package org.sdf.landscape;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test for {@link VerifiedLandscape}.
 */
final class VerifiedLandscapeTest {
    @Test
    void throwsWhenViolatedMaximumSize() {
        final int[] heights = new int[100_000];
        final var landscape = new VerifiedLandscape(
            new LandscapeOf(
                heights
            )
        );
        Assertions.assertThrows(
            IllegalStateException.class,
            landscape::heights
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 32001, Integer.MAX_VALUE, Integer.MIN_VALUE})
    void throwsWhenHeightIsInvalid(final int invalid) {
        final int[] heights = new int[100];
        heights[heights.length - 1] = invalid;
        final var landscape = new VerifiedLandscape(
            new LandscapeOf(heights)
        );
        Assertions.assertThrows(
            IllegalStateException.class,
            landscape::heights
        );
    }
}