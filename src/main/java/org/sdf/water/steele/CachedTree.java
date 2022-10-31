package org.sdf.water.steele;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CachedTree.
 */
public sealed interface CachedTree
    permits CachedTree.Null, CachedTree.Singleton, CachedTree.Pair {
    /**
     * Value.
     * @return Numeric value.
     */
    int value();


    /**
     * Build from array.
     * @param values Heights.
     * @return CachedTree.
     */
    static CachedTree from(final int... values) {
        return CachedTree.from(
            IntStream.of(values)
                .boxed()
                .collect(Collectors.toList())
        );
    }

    /**
     * Build from list.
     * @param values Heights.
     * @return CachedTree.
     */
    static CachedTree from(final List<Integer> values) {
        final CachedTree result;
        if (values.isEmpty()) {
            result = new Null();
        } else if (values.size() == 1) {
            result = new Singleton(values.get(0));
        } else {
            final var parts = Lists.split(values);
            final var a = CachedTree.from(parts.get(0));
            final var b = CachedTree.from(parts.get(1));
            result = new Pair(
                Math.max(
                    a.value(),
                    b.value()
                ),
                a,
                b
            );
        }

        return result;
    }

    /**
     * Null node.
     */
    record Null()
        implements CachedTree {
        @Override
        public int value() {
            throw new UnsupportedOperationException("Null has no value");
        }
    }

    /**
     * Leaf.
     * @param value Number.
     */
    record Singleton(int value)
        implements CachedTree {
    }

    /**
     * Join node.
     * @param value Number.
     * @param left Left node.
     * @param right Right node.
     */
    record Pair(
        int value,
        CachedTree left,
        CachedTree right
    ) implements CachedTree {
    }
}

