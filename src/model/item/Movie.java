package model.item;

/**
 * Movie extends ItemImpl taking his common field with Book. It forms the
 * archive with Book.
 *
 * @author Edoardo
 *
 */
public class Movie extends ItemImpl {

    @Override
    public String toString() {
        return super.toString() + "genre=" + this.genre + ", duration=" + this.duration + ", colour=" + this.color
                + "] - MOVIE";
    }

    private static final long serialVersionUID = 856227185802047288L;
    private final MovieGenre genre;
    private final Integer duration;
    private final Boolean color;

    /**
     * Movie's constructor.
     *
     * @param initTitle
     *            of the movie.
     * @param initReleaseYear
     *            of the movie.
     * @param initPublisher
     *            of the movie.
     * @param initAuthor
     *            director of the movie.
     * @param initCurrentLanguage
     *            of the movie contained in the archive.
     * @param initGenre
     *            of the movie.
     * @param initDuration
     *            of the movie (minutes)
     * @param initColor
     *            true == color, false == b/w.
     */
    public Movie(final String initTitle, final int initReleaseYear, final String initPublisher, final String initAuthor,
            final Language initCurrentLanguage, final MovieGenre initGenre, final Integer initDuration,
            final Boolean initColor) {
        super(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage);
        this.genre = initGenre;
        this.duration = initDuration;
        this.color = initColor;
    }

    /**
     *
     * @return movie's genre.
     */
    public MovieGenre getGenre() {
        return this.genre;
    }

    /**
     *
     * @return movie's duration.
     */
    public Integer getDuration() {
        return this.duration;
    }

    /**
     *
     * @return true == color, false == b/w.
     */
    public Boolean getColour() {
        return this.color;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }
}
