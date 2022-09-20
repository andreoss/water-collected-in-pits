package org.sdf.water;

import org.sdf.Landscape;
import org.sdf.WaterAmount;

/**
 * Naive solution.
 * Time: O(n * m)
 * Space: O(1)
 */
public final class NaiveWaterAmount implements WaterAmount {
    @Override
    public long collect(final Landscape landscape) {
        final int[] heights = landscape.heights();
        long water = 0;
        do {
            int hill;
            for (hill = heights.length - 1; hill >= 0; hill--) {
                if (heights[hill] > 0) {
                    break;
                }
            }
            int lowest = 0;
            for (int col = 0; col <= hill; col++) {
                if (heights[col] > 0) {
                    heights[col]--;
                    lowest += 1;
                } else if (lowest > 0) {
                    water++;
                }
            }
            if (lowest < 2) {
                break;
            }
        } while (true);
        return water;
    }
}
