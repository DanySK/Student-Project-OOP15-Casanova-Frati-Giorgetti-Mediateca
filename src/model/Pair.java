package model;

/**
 * This class is taken from the lessons' slide. It manages a pair of generic
 * type.
 *
 * @author Edoardo
 *
 * @param <X>
 * @param <Y>
 */
public class Pair<X, Y> {

    private X first;
    private Y second;

    /**
     * Pair's constructor.
     *
     * @param initFirst
     *            generic.
     * @param initSecond
     *            generic.
     */
    public Pair(final X initFirst, final Y initSecond) {
        this.first = initFirst;
        this.second = initSecond;
    }

    /**
     * First element getter.
     *
     * @return the first element.
     */
    public X getFirst() {
        return this.first;
    }

    /**
     * Second element getter.
     *
     * @return the second element.
     */
    public Y getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "<" + this.first + "," + this.second + ">";
    }
}
