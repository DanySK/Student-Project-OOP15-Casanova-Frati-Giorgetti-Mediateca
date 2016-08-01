package model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.item.ArchiveImpl.TypeItem;
import model.item.ItemGenre;
import model.item.Language;

/**
 * Interface for a generic model. It defines the main methods of the class
 * model, it's effectively the 'Mediateca' itself with user and item archives.
 *
 * @author Edoardo
 *
 */
public interface Model {

  /**
   * This method registers a user into the user archive and set its preferences.
   *
   * @param initName
   *          user's name.
   * @param initSurname
   *          user's surname.
   * @param initBirthdate
   *          date user's birth.
   * @param initUsername
   *          user's user-name.
   * @param initPassword
   *          user's password.
   * @param initEmail
   *          user's email.
   * @param initTelephoneNumber
   *          user's telephone number.
   * @param initBookPref
   *          user's list of BookGenre preferences.
   * @param initMoviePref
   *          user's list of MovieGenre preferences.
   * @throws Exception
   *           in the case which Users already is into the archive.
   */
  void registerUser(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
                          throws Exception;

  /**
   * This method removes the user with userId from the archive.
   *
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           in the case which userId is not in the archive.
   */
  void deleteUser(final int userId) throws Exception;

  /**
   * This method registers the book into the item archive.
   *
   * @param initTitle
   *          item's title.
   * @param initReleaseYear
   *          item's release year.
   * @param initAuthor
   *          item's author.
   * @param initCurrentLanguage
   *          item's language.
   * @param initIsbn
   *          book's code.
   * @param initGenre
   *          item's genre.
   * @param initPublisher
   *          item's publisher.
   * @param initNumRelease
   *          item's num of release.
   * @param initNumCopy
   *          item's num of copy available.
   * @throws Exception
   *           in the case which initNumCopy <= 0.
   */
  void registerBook(final String initTitle, final int initReleaseYear, final String initAuthor,
              final Language initCurrentLanguage, final String initIsbn, final ItemGenre initGenre,
              final String initPublisher, final Integer initNumRelease, final Integer initNumCopy)
                          throws Exception;

  /**
   * This method registers the book into the item archive.
   *
   * @param initTitle
   *          item's title.
   * @param initReleaseYear
   *          item's release year.
   * @param initPublisher
   *          item's publisher.
   * @param initAuthor
   *          item's author.
   * @param initCurrentLanguage
   *          item's language.
   * @param initGenre
   *          item's genre.
   * @param initDuration
   *          item's duration in min.
   * @param initColor
   *          item's color.
   * @param initNumCopy
   *          item's num of copy available.
   * @throws Exception
   *           in the case which initNumCOpy <= 0
   */
  void registerMovie(final String initTitle, final int initReleaseYear, final String initPublisher,
              final String initAuthor, final Language initCurrentLanguage,
              final ItemGenre initGenre, final Integer initDuration, final Boolean initColor,
              final Integer initNumCopy) throws Exception;

  /**
   * This method removes an item from the archive.
   *
   * @param itemId
   *          item's identifier.
   * @throws Exception
   *           in the case which itemId is not into the archive.
   */
  void deleteItem(final int itemId) throws Exception;

  /**
   * This method is used to associate a book with a user that borrow it.
   *
   * @param itemId
   *          item's identifier.
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           in the case which itemId or userId is not contained into their
   *           respective archive.
   */
  void borrowItem(final int itemId, final int userId) throws Exception;

  /**
   * This method is used to dissociate a book with a user that return it.
   *
   * @param itemId
   *          item's identifier.
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           in the case which itemId or userId is not contained into their
   *           respective archives or userId is not associated to itemId.
   */
  void returnItem(final int itemId, final int userId) throws Exception;

  /**
   * This method adds a like to the itemId by userId.
   *
   * @param itemId
   *          item's identifier.
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           in the which itemId or userId is not contained into their
   *           archives.
   */
  void addLike(final int itemId, final int userId) throws Exception;

  /**
   * This method returns a set of integer which refer to every item contained
   * into the archive. If you pass TypeItem.BOOK it return a set of books
   * identifier. If you pass TypeItem.MOVIE it return a set of movie identifier.
   *
   * @param type
   *          item's type
   * @return a set of item identifier contained into the archive.
   */
  Set<Integer> getAllItemId(TypeItem type);

  /**
   * This method return a map with key the itemId and as value the difference of
   * day between when the item was taken and today.
   *
   * @param userId
   *          user's identifier.
   * @return Map<Integer,Double> key = itemId, value = days.
   * @throws Exception
   *           in the case which userId is not in the archive.
   */
  Map<Integer, Double> checkDeadlineas(final Integer userId) throws Exception;

  /**
   * This method adds a review to the different archives.
   *
   * @param itemId
   *          item's identifier.
   * @param userId
   *          user's identifier.
   * @param vote
   *          review's vote.
   * @param note
   *          review's note.
   * @throws Exception
   *           in the case which itemId or userId is not in the archive.
   */
  void addReview(final Integer itemId, final Integer userId, final Integer vote, final String note)
              throws Exception;

  /**
   * This method return a set of Book or Movie with respective Genre.
   *
   * @param type
   *          Item's type.
   * @param genre
   *          Item's genre.
   * @return set of item with required genre.
   *
   * @throws Exception
   *           in the case which t is not BOOK or MOVIE.
   */
  Set<Integer> filterItemGenre(final TypeItem type, final ItemGenre genre) throws Exception;

  /**
   * This method update userId's recommended list of Book and Movie.
   *
   * @param userId
   *          user's identifier.
   */
  void refreshRecommendedList(final Integer userId);
}
