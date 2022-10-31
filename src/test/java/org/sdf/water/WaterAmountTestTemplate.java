package org.sdf.water;

import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sdf.Landscape;
import org.sdf.WaterAmount;
import org.sdf.landscape.LandscapeOf;
import org.sdf.landscape.RandomLandscape;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class WaterAmountTestTemplate {

    private final WaterAmount amount;

    static Iterable<Arguments> simple() {
        return List.of(
            Arguments.of(new LandscapeOf(0, 0), 0L), Arguments.of(new LandscapeOf(1, 0), 0L), Arguments.of(new LandscapeOf(0, 1), 0L), Arguments.of(new LandscapeOf(0, 0, 0), 0L), Arguments.of(new LandscapeOf(0, 1, 0), 0L), Arguments.of(new LandscapeOf(1, 0, 0), 0L), Arguments.of(new LandscapeOf(0, 0, 1), 0L), Arguments.of(new LandscapeOf(1, 0, 1), 1L), Arguments.of(new LandscapeOf(1, 1, 1), 0L), Arguments.of(new LandscapeOf(0, 1, 2), 0L), Arguments.of(new LandscapeOf(2, 1, 0), 0L), Arguments.of(new LandscapeOf(100, 0, 100), 100L), Arguments.of(new LandscapeOf(0, 0, 100), 0L), Arguments.of(new LandscapeOf(1, 0, 100), 1L), Arguments.of(new LandscapeOf(9, 1, 9), 8L), Arguments.of(new LandscapeOf(9, 1, 1, 9), 16L), Arguments.of(new LandscapeOf(9, 1, 1, 1, 9), 24L), Arguments.of(new LandscapeOf(3, 2, 3, 2, 3), 2L), Arguments.of(new LandscapeOf(1, 0, 1, 0, 1, 0, 1, 0, 1), 4L), Arguments.of(new LandscapeOf(0, 1, 0, 1, 0, 1, 0, 1, 0), 3L), Arguments.of(new LandscapeOf(6, 3, 5), 2L), Arguments.of(new LandscapeOf(2, 6, 3, 5, 2, 8), 8L), Arguments.of(new LandscapeOf(2, 6, 3, 5, 2, 8, 1, 4), 11L), Arguments.of(new LandscapeOf(2, 6, 3, 5, 2, 8, 1, 4, 2, 2, 5, 3, 5, 7, 4, 1), 35L), Arguments.of(new LandscapeOf(8, 1, 4), 3L), Arguments.of(new LandscapeOf(8, 1, 4, 2, 2), 3L), Arguments.of(new LandscapeOf(8, 1, 4, 2, 2, 5), 11L), Arguments.of(new LandscapeOf(8, 1, 4, 2, 2, 5, 3), 11L), Arguments.of(new LandscapeOf(8, 1, 4, 2, 2, 5, 3, 5), 13L), Arguments.of(new LandscapeOf(8, 1, 4, 2, 2, 5, 3, 5, 7), 27L), Arguments.of(new LandscapeOf(5, 2, 3, 4, 5, 4, 0, 3, 1), 9L));
    }

    static Iterable<Arguments> huge() {
        final var heights = new int[32000];
        Arrays.fill(heights, 32000);
        return List.of(Arguments.of(new LandscapeOf(heights), 0L));
    }

    @ParameterizedTest
    @MethodSource({"simple", "huge"})
    void shouldCalculateWaterAmountMaxCachedTree(
        final Landscape landscape, final long result
    ) {
        MatcherAssert.assertThat(
            "%s should contain %d units of water"
                .formatted(
                    landscape,
                    result
                ),
            this.amount.collect(landscape),
            Matchers.is(result)
        );
    }

    @ParameterizedTest
    @ValueSource(classes = {
        DivideAndConquerWaterAmount.class,
        ImprovedSinglePassWaterAmount.class,
        ImprovedWaterAmount.class,
        MaxCachedTreeWaterAmount.class,
        NaiveWaterAmount.class
    })
    void shouldHaveTheSameResultAsOtherImplementation(
        final Class<? extends WaterAmount> type
    ) throws Exception {
        final var landscape = new RandomLandscape(32000, 32000);
        MatcherAssert.assertThat(
            this.amount.collect(landscape),
            Matchers.is(
                type
                    .getDeclaredConstructor()
                    .newInstance()
                    .collect(landscape)
            )
        );
    }


}