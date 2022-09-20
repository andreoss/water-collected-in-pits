package org.sdf.water;

import java.util.Arrays;
import org.sdf.Landscape;
import org.sdf.WaterAmount;
import org.sdf.water.steele.Glob;

/**
 * Divide & Conquer implementation of {@link WaterAmount}.
 */
public final class DivideAndConquerWaterAmount implements WaterAmount {
    @Override
    public long collect(final Landscape landscape) {
        return Arrays
            .stream(landscape.heights())
            .parallel()
            .mapToObj(Glob::singleton)
            .reduce(Glob.empty(), Glob::combine)
            .water();
    }
}
