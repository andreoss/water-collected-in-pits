package org.sdf.water.steele;

import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Split};
 */
final class SplitTest {

    @Test
    void shouldBeEmptySplitForEmptyList() {
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(),
                2
            ),
            Matchers.is(
                new Split(
                    List.of(), Optional.empty(), List.of()
                )
            )
        );
    }


    @Test
    void shouldBeOneWaySplit() {
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(new Plateu(1, 1)),
                1
            ),
            Matchers.is(new Split(
                List.of(), Optional.of(1), List.of()
            ))
        );
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(new Plateu(2, 2)),
                1
            ),
            Matchers.is(
                new Split(
                    List.of(),
                    Optional.empty(),
                    List.of(new Plateu(2, 2))
                )
            )
        );
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(new Plateu(2, 10)),
                3
            ),
            Matchers.is(
                new Split(
                    List.of(new Plateu(2, 10)),
                    Optional.empty(),
                    List.of()
                )
            )
        );
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(new Plateu(10, 10)),
                3
            ),
            Matchers.is(
                new Split(
                    List.of(),
                    Optional.empty(),
                    List.of(new Plateu(10, 10))
                )
            )
        );
    }

    @Test
    void shouldSplitInTheMiddle2() {
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(
                    new Plateu(2, 2),
                    new Plateu(4, 2)
                ),
                5
            ),
            Matchers.is(
                new Split(
                    List.of(
                        new Plateu(2, 2),
                        new Plateu(4, 2)
                    ),
                    Optional.empty(),
                    List.of()
                )
            )
        );
    }

    @Test
    void shouldSplitInTheMiddle() {
        MatcherAssert.assertThat(
            Split.threeWay(
                List.of(
                    new Plateu(1, 1),
                    new Plateu(3, 3),
                    new Plateu(1, 1)
                ),
                2
            ),
            Matchers.is(
                new Split(
                    List.of(new Plateu(1, 1)),
                    Optional.empty(),
                    List.of(
                        new Plateu(3, 3),
                        new Plateu(1, 1)
                    )
                )
            )
        );
    }
}