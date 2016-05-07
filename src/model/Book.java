package model;

import com.google.common.base.Optional;

/**
 * Book extends ItemImpl taking its common field with movie. It forms the
 * archive with Movie.
 *
 * @author Edoardo
 *
 */
public class Book extends ItemImpl {

    private static final long serialVersionUID = -7358930538078727479L;
    private final String isbn;
    private final BookGenre genre;
    private Optional<Integer> numRelease = Optional.absent();

    /**
     * Book's constructor.
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
     */
    public Book(final String initTitle, final int initReleaseYear, final String initAuthor,
            final Language initCurrentLanguage, final String initPathCover, final String initISBN,
            final BookGenre initGenre, final String initPublisher, final Integer initNumRelease) {
        super(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage, initPathCover);
        this.isbn = initISBN;
        this.genre = initGenre;
        this.numRelease = initNumRelease == null ? Optional.of(1) : Optional.of(initNumRelease);
    }
}
