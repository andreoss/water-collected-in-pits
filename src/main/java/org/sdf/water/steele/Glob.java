package org.sdf.water.steele;

import java.util.List;

/**
 * Mergable landscape.
 *
 * @param left Plateus to the left.
 * @param top Top plateu.
 * @param right Plateus to the right.
 * @param water Amount of water.
 */
public record Glob(
    List<Plateu> left,
    Plateu top,
    List<Plateu> right,
    long water
) {

    /**
     * Empty glob.
     * @return Glob.
     */
    public static Glob empty() {
        return new Glob(
            List.of(),
            new Plateu(Integer.MIN_VALUE, 0),
            List.of(),
            0L
        );
    }


    /**
     * Create from a sigle height unit.
     * @param height Height.
     * @return Glob.
     */
    public static Glob singleton(final int height) {
        return new Glob(
            List.of(),
            new Plateu(height, 1),
            List.of(),
            0L
        );
    }
    /**
     * Combine two globs.
     * @param other Second glob.
     * @return Glob.
     */
    public Glob combine(Glob other) {
        final Glob result;
        if (this.top.height() < other.top.height()) {
            final Split split = Split.threeWay(other.left(), this.top().height());
            result = new Glob(
                Lists.concat(
                    this.left(),
                    List.of(
                        new Plateu(
                            this.top().height(),
                            this.top().width()
                                + Plateu.width(this.right())
                                + Plateu.width(split.lesser())
                                + split.equal().orElse(0)
                        )
                    ),
                    split.greater()
                ),
                other.top(),
                other.right(),
                this.water()
                    + Plateu.fill(this.right(), this.top().height())
                    + Plateu.fill(split.lesser(), this.top().height())
                    + other.water()
            );
        } else if (this.top.height() > other.top.height()) {
            final Split split = Split.threeWay(this.right(), other.top().height());
            result = new Glob(
                this.left(),
                this.top(),
                Lists.concat(
                    other.right(),
                    List.of(
                        new Plateu(
                            other.top.height(),
                            split.equal().orElse(0)
                                + Plateu.width(split.lesser())
                                + Plateu.width(other.left())
                                + other.top().width()
                        )
                    ),
                    split.greater()
                ),
                this.water()
                    + Plateu.fill(split.lesser(), other.top().height())
                    + Plateu.fill(other.left(), other.top().height())
                    + other.water()
            );
        } else {
            result = new Glob(
                this.left(),
                new Plateu(
                    this.top().height(),
                    this.top().width()
                        + other.top().width()
                        + Plateu.width(this.right())
                        + Plateu.width(other.left())
                ),
                other.right(),
                this.water()
                    + Plateu.fill(this.right(), this.top().height())
                    + Plateu.fill(other.left(), this.top().height())
                    + other.water()
            );

        }
        return result;
    }


}
