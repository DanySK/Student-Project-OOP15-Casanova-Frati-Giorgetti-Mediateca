package model;

import java.awt.Image;

import com.google.common.base.Optional;

/**
 * Book extends ItemImpl taking its common field with movie. It forms the
 * archive with movie.
 *
 * @author Edoardo
 *
 */
public class Book extends ItemImpl {

    private static final long serialVersionUID = -7358930538078727479L;
    private final String isbn;
    private final BookGenre genre;
    private final String publisher;
    private Optional<Integer> numRelease = Optional.absent();
    private final Language originalLanguage;

    /**
     *
     * @param title
     *            book's title
     * @param releaseYear
     *            year of the book release
     * @param author
     *            who wrote the book
     * @param cover
     *            of the book
     * @param ISBN
     *            International Standard Book Number
     * @param genre
     *            of the book
     * @param publisher
     *            of the book
     * @param numRelease
     *            if there is more then one
     * @param originalLanguage
     *            of the book
     */
    public Book(final String title, final int releaseYear, final String author, final Image cover, final String isbn,
            final BookGenre genre, final String publisher, final Optional<Integer> numRelease,
            final Language originalLanguage) {
        super(title, releaseYear, author, cover);
        this.isbn = isbn;
        this.genre = genre;
        this.publisher = publisher;
        this.numRelease = numRelease;
        this.originalLanguage = originalLanguage;
    }

    /**
     * Book Builder.
     *
     * @author Edoardo
     *
     */
    public static class BuilderBook extends ItemImpl.BuilderItem {

        private String isbn;
        private BookGenre genre;
        private String publisher;
        private Optional<Integer> numRelease = Optional.absent();
        private Language originalLanguage;

        /**
         * Build isbn field.
         *
         * @param s
         *            whit the ISBN code
         * @return isbn
         */
        public BuilderBook isbn(final String s) {
            this.isbn = s;
            return this;
        }

        /**
         * Build genre field.
         *
         * @param g
         *            book genre
         * @return genre
         */
        public BuilderBook genre(final BookGenre g) {
            this.genre = g;
            return this;
        }

        /**
         * Build publisher field.
         *
         * @param s
         *            publisher name
         * @return publisher
         */
        public BuilderBook publisher(final String s) {
            this.publisher = s;
            return this;
        }

        /**
         * if i is present numRelease = i else it is the first release.
         *
         * @param i
         *            num of release
         * @return numRelease
         */
        public BuilderBook numRelease(final int i) {
            if (Optional.of(i).isPresent()) {
                this.numRelease = Optional.fromNullable(i);
            } else {
                this.numRelease = Optional.of(1);
            }
            return this;
        }

        /**
         * Build originalLanguage field.
         *
         * @param l
         *            originalLanguage
         * @return originalLanguage
         */
        public BuilderBook originalLanguage(final Language l) {
            this.originalLanguage = l;
            return this;
        }

        @Override
        public Book build() throws IllegalStateException {
            if ((this.isbn == null) || (this.numRelease == null) || (this.publisher == null)) {
                throw new IllegalStateException("Book builder - Param error");
            }
            return new Book(super.title, super.releaseYear, super.author, super.cover, this.isbn, this.genre,
                    this.publisher, this.numRelease, this.originalLanguage);
        }
    }
}
