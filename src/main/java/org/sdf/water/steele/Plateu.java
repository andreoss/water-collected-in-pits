package org.sdf.water.steele;

import java.util.Collection;

/**
 * Plateu in a landscape.
 * @param height Height.
 * @param width Width.
 */
public record Plateu(int height, int width) {

    /**
     * Width of plateus.
     * @param plateus Plateus.
     * @return Width.
     */
    public static int width(final Collection<Plateu> plateus) {
        return plateus.stream().mapToInt(Plateu::width).sum();
    }

    /**
     * How much water fills in.
     * @param plateus Platues.
     * @param level
     * @return
     */
    public static int fill(final Collection<Plateu> plateus, final int level) {
        return plateus.stream().mapToInt(
            p -> p.width() * (level - p.height())
        ).sum();
    }

}
