package model.item;

import java.util.Formatter;

import com.google.common.base.Optional;

import utils.ItemGenre;
import utils.Language;

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
    StringBuilder stringBuilder = new StringBuilder();
    Formatter formatter = new Formatter(stringBuilder);
    String template = " | %13s";
    formatter.format(template, this.getIsbn());
    formatter.close();
    return " " + super.toString() + stringBuilder.toString();
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
    return this.numRelease;
  }

  /**
   *
   * @return ISBN code.
   */
  public String getIsbn() {
    return this.isbn;
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
