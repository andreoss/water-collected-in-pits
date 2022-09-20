package org.sdf.water.steele;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

final class CachedTreeTest {
    @Test
    void shouldBuildAMaxCachedTreeEmpty() {
        MatcherAssert.assertThat(
            CachedTree.from(),
            Matchers.is(
                new CachedTree.Null()
            )
        );
    }

    @Test
    void shouldBuildAMaxCachedTree() {
        MatcherAssert.assertThat(
            CachedTree.from(1, 0, 1),
            Matchers.is(
                new CachedTree.Pair(
                    1,
                    new CachedTree.Singleton(1),
                    new CachedTree.Pair(1,
                        new CachedTree.Singleton(0),
                        new CachedTree.Singleton(1)
                    )
                )
            )
        );
    }
}