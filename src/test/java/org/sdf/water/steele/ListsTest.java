package org.sdf.water.steele;

import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link Lists}.
 */
final class ListsTest {
    @Test
    void shouldJoinTwoLists() {
        MatcherAssert.assertThat(
            Lists.concat(
                List.of(10, 9),
                List.of(8, 7, 6)
            ),
            Matchers.equalTo(
                List.of(10, 9, 8, 7, 6)
            )
        );
    }

    @Test
    void shouldSplitInTwoNotEven() {
        MatcherAssert.assertThat(
            Lists.split(
                List.of(10, 9, 8, 7, 6)
            ),
            Matchers.contains(
                List.of(10, 9),
                List.of(8, 7, 6)
            )
        );
    }

    @Test
    void shouldSplitInTwoEvenElems() {
        MatcherAssert.assertThat(
            Lists.split(
                List.of(1, 2, 3, 4)
            ),
            Matchers.contains(
                Matchers.hasItems(1, 2),
                Matchers.hasItems(3, 4)
            )
        );
    }
}