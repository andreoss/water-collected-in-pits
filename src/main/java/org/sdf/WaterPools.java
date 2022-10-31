package org.sdf;

import org.sdf.landscape.LandscapeOf;
import org.sdf.landscape.VerifiedLandscape;
import org.sdf.water.DivideAndConquerWaterAmount;

/**
 * Main.
 */
public final class WaterPools {
    /**
     * Method with the required signature.
     * @param heights Array of heghts.
     * @return Amount of water.
     */
    public static long calculateWaterAmount(final int[] heights) {
        return new DivideAndConquerWaterAmount().collect(
            new VerifiedLandscape(
                new LandscapeOf(heights)
            )
        );
    }
}