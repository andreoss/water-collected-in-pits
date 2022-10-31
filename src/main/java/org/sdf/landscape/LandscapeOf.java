package org.sdf.landscape;

import java.util.Arrays;
import lombok.ToString;
import org.sdf.Landscape;

/**
 * Landscape from array of ints.
 */
@ToString
public final class LandscapeOf implements Landscape {

    /**
     * Heights.
     */
    private final int[] values;

    /**
     * Ctor.
     * @param values Heights.
     */
    public LandscapeOf(final int... values) {
        this.values = values;
    }

    @Override
    public int[] heights() {
        return Arrays.copyOf(
            this.values,
            this.values.length
        );
    }

}