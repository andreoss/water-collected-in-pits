package org.sdf.water;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.sdf.Landscape;
import org.sdf.WaterAmount;
import org.sdf.water.steele.CachedTree;

/**
 * Tree implementation of WaterAmount
 * O(n)
 */
public final class MaxCachedTreeWaterAmount implements WaterAmount {
    private final Executor executor;

    /**
     * Ctor.
     * @param executor Exector.
     */
    public MaxCachedTreeWaterAmount(final Executor executor) {
        this.executor = executor;
    }

    /**
     * Ctor (will use default executor).
     */
    public MaxCachedTreeWaterAmount() {
        this(Executors.newWorkStealingPool());
    }

    @Override
    public long collect(final Landscape landscape) {
        return this.process(
            CachedTree.from(landscape.heights()),
            Long.MIN_VALUE,
            Long.MIN_VALUE
        ).join();
    }

    /**
     * Process tree.
     * @param tree Tree.
     * @param lmax Left maximum.
     * @param rmax Right maximum
     * @return Amount.
     */
    private CompletableFuture<Long> process(
        final CachedTree tree, final long lmax, final long rmax
    ) {
        return switch (tree) {
            case CachedTree.Null() ignored ->
                CompletableFuture.supplyAsync(() -> 0L, this.executor);
            case CachedTree.Pair(int ignored,CachedTree a,CachedTree b) ->
                this.combine(a, b, lmax, rmax);
            case CachedTree.Singleton(int value) ->
                CompletableFuture.supplyAsync(
                    () ->
                        Math.max(
                            Math.min(lmax, rmax), value
                        ) - value,
                    this.executor
                );
        };
    }

    /**
     * Process Pair node.
     * @param left Node.
     * @param right Node.
     * @param rmax Left maximum.
     * @param lmax Right maxium.
     * @return Amount.
     */
    private CompletableFuture<Long> combine(
        final CachedTree left,
        final CachedTree right,
        final long rmax,
        final long lmax
    ) {
        return this.process(
            left,
            rmax,
            Math.max(right.value(), lmax)
        ).thenCombineAsync(
            this.process(
                right,
                Math.max(rmax, left.value()),
                lmax
            ),
            Long::sum
        );
    }
}
