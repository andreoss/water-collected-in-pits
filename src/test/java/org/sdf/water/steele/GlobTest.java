package org.sdf.water.steele;


import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Glob}.
 */
final class GlobTest {

    @Test
    void shouldCompileEmptyAndEmpty() {
        final Glob sum = Glob.empty().combine(Glob.empty());
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(),
                    new Plateu(Integer.MIN_VALUE, 0),
                    List.of(),
                    0L
                )
            )
        );
    }

    @Test
    void shouldCompileEmptyAndSignleton() {
        final Glob sum = Glob.empty().combine(Glob.singleton(1));
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(1, 1),
                    List.of(),
                    0L
                )
            )
        );
    }

    @Test
    void shouldCombineTwoSingletons() {
        final Glob sum = Glob.singleton(1).combine(Glob.singleton(0));
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(),
                    new Plateu(1, 1),
                    List.of(new Plateu(0, 1)),
                    0L
                )
            )
        );
    }

    @Test
    void shouldCombineTwoSingletonsReverse() {
        final Glob sum = Glob.singleton(0).combine(Glob.singleton(1));
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(new Plateu(0, 1)),
                    new Plateu(1, 1),
                    List.of(),
                    0L
                )
            )
        );
    }

    @Test
    void shouldCombineToOnePleateu() {
        final Glob sum = Glob
            .singleton(1)
            .combine(Glob.singleton(0))
            .combine(Glob.singleton(0))
            .combine(Glob.singleton(1));
        MatcherAssert.assertThat(
            sum.water(),
            Matchers.is(2L)
        );
    }

    @Test
    void shouldCombineSeveralPlateusComplexCase3() {
        final Glob sum3 =
            new Glob(
                List.of(new Plateu(Integer.MIN_VALUE, 0)),
                new Plateu(8, 1),
                List.of(
                    new Plateu(2, 2),
                    new Plateu(4, 2)
                ),
                3L
            ).combine(Glob.singleton(5));
        MatcherAssert.assertThat(
            sum3,
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(8, 1),
                    List.of(
                        new Plateu(5, 5)
                    ),
                    11L
                )
            )
        );
    }

    @Test
    void shouldCombineSeveralPlateusComplexCase2() {
        final Glob sum = Glob
            .empty()
            .combine(Glob.singleton(8))
            .combine(Glob.singleton(1))
            .combine(Glob.singleton(4));
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(8, 1),
                    List.of(
                        new Plateu(4, 2)
                    ),
                    3L
                )
            )
        );
        MatcherAssert.assertThat(
            sum
                .combine(Glob.singleton(2))
                .combine(Glob.singleton(2)),
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(8, 1),
                    List.of(
                        new Plateu(2, 2),
                        new Plateu(4, 2)
                    ),
                    3L
                )
            )
        );
        MatcherAssert.assertThat(
            sum
                .combine(Glob.singleton(2))
                .combine(Glob.singleton(2)).combine(Glob.singleton(5)),
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(8, 1),
                    List.of(
                        new Plateu(5, 5)
                    ),
                    11L
                )
            )
        );
    }

    @Test
    void shouldCombineSeveralPlateusComplexCase() {
        final Glob sum = Glob
            .empty()
            .combine(Glob.singleton(8))
            .combine(Glob.singleton(1))
            .combine(Glob.singleton(4))
            .combine(Glob.singleton(2))
            .combine(Glob.singleton(2))
            .combine(Glob.singleton(5))
            .combine(Glob.singleton(3));
        MatcherAssert.assertThat(
            sum,
            Matchers.is(
                new Glob(
                    List.of(new Plateu(Integer.MIN_VALUE, 0)),
                    new Plateu(8, 1),
                    List.of(
                        new Plateu(3, 1),
                        new Plateu(5, 5)
                    ),
                    11L
                )
            )
        );
    }

    @Test
    void shouldCombineWithEmpty() {
        final Glob sum = Glob
            .singleton(1)
            .combine(Glob.singleton(0));
        MatcherAssert.assertThat(
            sum.water(),
            Matchers.is(0L)
        );
    }

    @Test
    void shouldCombineWithEmptyTwice() {
        final var first = Glob
            .singleton(1)
            .combine(Glob.singleton(0));
        final Glob sum = first
            .combine(Glob.singleton(0));
        MatcherAssert.assertThat(
            sum.water(),
            Matchers.is(0L)
        );
    }
}