package org.sdf.water.steele;

import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class PlateuTest {
    @Test
    void calculatesFill() {
        MatcherAssert.assertThat(
            Plateu.fill(
                List.of(
                    new Plateu(4, 1),
                    new Plateu(4, 2),
                    new Plateu(4, 3)
                ),
                10
            ),
            Matchers.is(36)
        );
    }

    @Test
    void calculatesNewWidth() {
        MatcherAssert.assertThat(
            Plateu.width(
                List.of(
                    new Plateu(1, 1),
                    new Plateu(1, 2),
                    new Plateu(1, 3)
                )
            ),
            Matchers.is(6)
        );
    }
}