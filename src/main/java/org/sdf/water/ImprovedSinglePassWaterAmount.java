package org.sdf.water;

import org.sdf.Landscape;
import org.sdf.WaterAmount;

/**
 * Single pass implementation of {@link ImprovedWaterAmount}.
 */
public final class ImprovedSinglePassWaterAmount implements WaterAmount {
    @Override
    public long collect(final Landscape landscape) {
        final int[] heights = landscape.heights();
        long result = 0;
        int lind = 0;
        int rind = heights.length - 1;
        int lmax = heights[lind];
        int rmax = heights[rind];
        while (lind < rind) {
            if (heights[lind] <= heights[rind]) {
                lmax = Math.max(heights[lind], lmax);
                result += lmax - heights[lind];
                lind++;
            } else {
                rmax = Math.max(heights[rind], rmax);
                result += rmax - heights[rind];
                rind--;
            }
        }
        return result;
    }
}
