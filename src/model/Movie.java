package model;

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
        return super.toString_1() + "Movie [genre=" + this.genre + ", duration=" + this.duration + ", colour="
                + this.colour + "]";
    }

    private static final long serialVersionUID = 856227185802047288L;
    private final MovieGenre genre;
    private final Integer duration;
    private final Boolean colour;

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
     * @param initPathCover
     *            of the movie.
     * @param initGenre
     *            of the movie.
     * @param initDuration
     *            of the movie (minutes)
     * @param initColour
     *            true == color, false == b/w.
     */
    public Movie(final String initTitle, final int initReleaseYear, final String initPublisher, final String initAuthor,
            final Language initCurrentLanguage, final String initPathCover, final MovieGenre initGenre,
            final Integer initDuration, final Boolean initColour) {
        super(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage, initPathCover);
        this.genre = initGenre;
        this.duration = initDuration;
        this.colour = initColour;
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
        return this.colour;
    }
}
