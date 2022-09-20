package org.sdf.water;

import org.sdf.Landscape;
import org.sdf.WaterAmount;

/**
 * Optimized implementation.
 *
 * Time: O(n)
 * Space: O(n)
 */
public final class ImprovedWaterAmount implements WaterAmount {
    @Override
    public long collect(final Landscape landscape) {
        final int[] heights = landscape.heights();
        final int[] levels = this.levels(heights);
        long result = 0;
        for (int i = 0; i < heights.length; i++) {
            result += levels[i] - heights[i];
        }
        return result;
    }

    /**
     * Calculate levels.
     * @param heights Heights.
     * @return Int array.
     */
    private int[] levels(final int[] heights) {
        final int[] right = this.maxWalkingForward(heights);
        final int[] left = this.maxWalkingBackward(heights);
        final int[] levels = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            levels[i] = Math.min(left[i], right[i]);
        }
        return levels;
    }

    /**
     * Calculate maximums from the left.
     * @param heights Heigts.
     * @return Int array.
     */
    private int[] maxWalkingBackward(final int[] heights) {
        final int[] result = new int[heights.length];
        int max = heights[heights.length - 1];
        for (int i = heights.length - 1; i >= 0; i--) {
            result[i] = Math.max(
                heights[i], max
            );
            max = result[i];
        }
        return result;
    }

    /**
     * Calculate maximums from the right.
     * @param heights Heights.
     * @return Int array.
     */
    private int[] maxWalkingForward(final int[] heights) {
        final int[] result = new int[heights.length];
        int max = heights[0];
        for (int i = 0; i < heights.length; i++) {
            result[i] = Math.max(
                heights[i], max
            );
            max = result[i];
        }
        return result;
    }
}
