package org.sdf.landscape;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.sdf.Landscape;

/**
 * Random generated landscape.
 */
@ToString
@RequiredArgsConstructor
public final class RandomLandscape implements Landscape {

    /**
     * Size.
     */
    private final int size;

    /**
     * Maximum height.
     */
    private final int max;

    /**
     * Generated landscape.
     */
    @ToString.Exclude
    private final AtomicReference<Landscape> generated = new AtomicReference<>();

    /**
     * Random.
     */
    @ToString.Exclude
    private final Random random = new Random();

    @Override
    public int[] heights() {
        return this.generated.updateAndGet(
            landscape -> {
                final Landscape result;
                if (landscape != null) {
                    result = landscape;
                } else {
                    final var heights = IntStream
                        .generate(() -> random.nextInt(this.max))
                        .limit(this.size)
                        .toArray();
                    result = new LandscapeOf(heights);
                }
                return result;
            }
        ).heights();
    }
}
