package org.sdf;

/**
 * Water amount collected from {@link Landscape}.
 */
public interface WaterAmount {
    /**
     * Amount.
     * @param landscape Landspace.
     * @return Units of water.
     */
    long collect(final Landscape landscape);

}
