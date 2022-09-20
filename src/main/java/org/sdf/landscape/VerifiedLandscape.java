package org.sdf.landscape;

import lombok.RequiredArgsConstructor;
import org.sdf.Landscape;

/**
 * Validated {@link Landscape}.
 */
@RequiredArgsConstructor
public final class VerifiedLandscape implements Landscape {
    /**
     * Max size of a landscape.
     */
    private static final int SIZE_MAX = 32000;

    /**
     * Max height of a landscape.
     */
    private static final int HEIGHT_MAX = 32000;

    /**
     * Min height of a landscape.
     */
    private static final int HEIGHT_MIN = 0;

    /**
     * Landscape to verify.
     */
    private final Landscape original;

    @Override
    public int[] heights() {
        final var heights = this.original.heights();
        if (heights.length > SIZE_MAX) {
            throw new IllegalStateException(
                "Maximum size of landscape is %d, %d was given"
                    .formatted(
                        SIZE_MAX,
                        heights.length
                    )
            );
        }
        for (int index = 0; index < heights.length; index++) {
            if (heights[index] < HEIGHT_MIN || heights[index] > HEIGHT_MAX) {
                throw new IllegalStateException(
                    "Height should be between %d and %d. Found %d at position %d"
                        .formatted(
                            HEIGHT_MIN,
                            SIZE_MAX,
                            heights[index],
                            index
                        )
                );
            }
        }
        return heights;
    }
}
