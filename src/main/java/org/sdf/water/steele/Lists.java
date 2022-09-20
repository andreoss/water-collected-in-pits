package org.sdf.water.steele;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

/**
 * List utilities.
 */
@UtilityClass
public final class Lists {

    /**
     * Concatenate lits.
     * @param elems Lists.
     * @return All elements concatenated in one list.
     * @param <X> Type of elements.
     */
    public static <X> List<X> concat(final List<X>... elems) {
        return Stream.of(elems).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Split list in two parts.
     * @param list List.
     * @return List of two lists.
     * @param <X> Type of elements.
     */
    public static <X> List<List<X>> split(final List<X> list) {
        final long middle = list.size() / 2;
        return List.of(
            list.stream().limit(middle).collect(Collectors.toList()),
            list.stream().skip(middle).collect(Collectors.toList())
        );
    }
}
