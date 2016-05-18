package model.item;

/**
 * This class is a Item Factory, it has two methods in order to create two
 * different objects (Book and Movie).
 *
 * @author Edoardo
 *
 */
public class ItemFactory {

    public ItemFactory() {
    }

    /**
     * This method return a new Book(...).
     *
     * @param initTitle
     *            book's title
     * @param initReleaseYear
     *            year of the book release
     * @param initAuthor
     *            who wrote the book
     * @param initCurrentLanguage
     *            of the book
     * @param initPathCover
     *            of the book
     * @param initISBN
     *            International Standard Book Number
     * @param initGenre
     *            of the book
     * @param initPublisher
     *            of the book
     * @param initNumRelease
     *            if there is more then one
     * @return new Book(...).
     */
    public static Book getNewBook(final String initTitle, final int initReleaseYear, final String initAuthor,
            final Language initCurrentLanguage, final String initPathCover, final String initISBN,
            final BookGenre initGenre, final String initPublisher, final Integer initNumRelease) {
        return new Book(initTitle, initReleaseYear, initAuthor, initCurrentLanguage, initPathCover, initISBN, initGenre,
                initPublisher, initNumRelease);
    }

    /**
     * This method return a new Movie(...).
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
     *
     * @return new Movie(...).
     */
    public static Movie getNewMovie(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage, final String initPathCover,
            final MovieGenre initGenre, final Integer initDuration, final Boolean initColour) {
        return new Movie(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage, initPathCover,
                initGenre, initDuration, initColour);
    }
}
