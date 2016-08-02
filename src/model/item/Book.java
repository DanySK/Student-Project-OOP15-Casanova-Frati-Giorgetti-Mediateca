package model.item;

import com.google.common.base.Optional;

/**
 * Book extends ItemImpl taking its common field with movie. It forms the
 * archive with Movie.
 *
 * @author Edoardo
 *
 */
public class Book extends ItemImpl {

  @Override
  public String toString() {
    return super.toString() + "Book [isbn=" + this.isbn + ", numRelease=" + this.numRelease
                + "] - BOOK";
  }

  private static final long serialVersionUID = -7358930538078727479L;
  private final String isbn;

  private Optional<Integer> numRelease = Optional.absent();

  /**
   * Book's constructor.
   *
   * @param initTitle
   *          book's title
   * @param initReleaseYear
   *          year of the book release
   * @param initAuthor
   *          who wrote the book
   * @param initCurrentLanguage
   *          of the book
   * @param initISBN
   *          International Standard Book Number
   * @param initGenre
   *          of the book
   * @param initPublisher
   *          of the book
   * @param initNumRelease
   *          if there is more then one, if you pass null the archive set 1
   */
  public Book(final String initTitle, final int initReleaseYear, final String initAuthor,
              final Language initCurrentLanguage, final String initISBN, final ItemGenre initGenre,
              final String initPublisher, final Integer initNumRelease) {
    super(initTitle, initReleaseYear, initPublisher, initAuthor, initCurrentLanguage, initGenre);
    this.isbn = initISBN.toUpperCase();
    this.numRelease = initNumRelease == null ? Optional.of(1) : Optional.of(initNumRelease);
  }

  /**
   *
   * @return num of release.
   */
  public Optional<Integer> getNumRelease() {
    Optional<Integer> i = this.numRelease;
    return i;
  }

  /**
   *
   * @return ISBN code.
   */
  public String getIsbn() {
    String s = this.isbn;
    return s;
  }

  @Override
  public int hashCode() {
    Integer i = super.hashCode();
    return i;
  }

  @Override
  public boolean equals(final Object o) {
    return super.equals(o);
  }
}
