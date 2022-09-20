package org.sdf.water.steele;

import java.util.List;
import java.util.Optional;

/**
 * Split list of plateus in three parts, if possibe.
 * @param lesser Lesser part.
 * @param equal Equal part.
 * @param greater Greater part.
 */
public record Split(
    List<Plateu> lesser,
    Optional<Integer> equal,
    List<Plateu> greater
) {

    /**
     * Split in three parts.
     * @param plateus Plateus.
     * @param desired Desired height.
     * @return Split object.
     */
    public static Split threeWay(
        final List<Plateu> plateus, final int desired
    ) {
        final Split result;
        if (plateus.isEmpty()) {
            result = new Split(
                List.of(), Optional.empty(), List.of()
            );
        } else if (plateus.size() == 1) {
            final var plateu = plateus.get(0);
            if (plateu.height() < desired) {
                result = new Split(
                    plateus, Optional.empty(), List.of()
                );
            } else if (plateu.height() > desired) {
                result = new Split(
                    List.of(), Optional.empty(), plateus
                );
            } else {
                result = new Split(
                    List.of(), Optional.of(plateu.width()), List.of()
                );
            }
        } else {
            final List<List<Plateu>> parts = Lists.split(plateus);
            if (desired < parts.get(1).get(0).height()) {
                final var split = Split.threeWay(
                    parts.get(0), desired
                );
                result = new Split(
                    split.lesser(),
                    split.equal(),
                    Lists.concat(split.greater(), parts.get(1))
                );
            } else {
                final var split = Split.threeWay(
                    parts.get(1), desired
                );
                result = new Split(
                    Lists.concat(parts.get(0), split.lesser()),
                    split.equal(),
                    split.greater()
                );
            }
        }
        return result;
    }


}
