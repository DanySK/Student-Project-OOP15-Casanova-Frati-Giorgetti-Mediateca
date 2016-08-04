package model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

import model.ModelImpl.TypeSearch;
import model.item.ArchiveImpl.TypeItem;
import model.item.Item;
import model.item.ItemGenre;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.Language;
import model.user.User;

/**
 * Interface for a generic model. It defines the main methods of the class
 * model, it's effectively the 'Mediateca' itself with user and item archives.
 *
 * @author Edoardo
 *
 */
public interface Model {

  /**
   * This method @return the item archive in order to be serialized.
   */
  Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive();

  /**
   * This method set the item archive in order to be (de)serialized.
   *
   * @param itemArchive
   *          item's archive.
   * @throws Exception
   *           in the case which the archive is been already initialized.
   */
  void setItemArchive(Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive) throws Exception;

  /**
   * This method @return the archiveUser in order to be serialized.
   */
  Map<Integer, User> getArchiveUser();

  /**
   * This method set the user archive in order to be (de)serialized.
   *
   * @param initArchiveUser
   *          the archiveUser to set
   * 
   * @throws Exception
   *           in the case which the archive is benne already initialized.
   */
  void setArchiveUser(final Map<Integer, User> initArchiveUser) throws Exception;

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
   * This method returns a set of integer which refer to every user contained
   * into the archive.
   *
   * @return a set of user identifier contained into the archive.
   */
  Set<Integer> getAllUserId();

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
   * This method return a specific Item in the archive white item identifier ==
   * itemId.
   *
   * @param itemId
   *          item's identifier.
   * @return Object Item.
   * @throws Exception
   *           in the case which item is not in the archive.
   */
  Item getRequiredItem(final Integer itemId) throws Exception;

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
   * This method return a set of itemId which required condition match with ts
   * and param. It is possible to pass as the first param a Set of integer where
   * search. If the set is NULL the method search in all the archive. In this
   * way you can chain different filters.
   *
   * @param set
   *          set where search.
   * @param ts
   *          Type of field search.
   * @param param
   *          to search in the archive
   *
   * @return set of item identifier with satisfy condition.
   * @throws Exception
   *           in the case which name is not into the archive.
   */
  Set<Integer> filterItem(final Optional<Set<Integer>> set, final TypeSearch ts, final String param)
              throws Exception;

  /**
   * This method update all userId's recommended list of Book and Movie.
   *
   * @throws Exception
   *           in the case which there is problem to get user whit userId
   *
   */
  void refreshRecommendedList() throws Exception;

  /**
   * This method is used by the user to book a place into the StudyRoom.
   *
   * @param day
   *          required.
   * @param sit
   *          required.
   * @param userId
   *          of the user.
   * @throws Exception
   *           in the case which sit/userId in not valid or it is busy.
   */
  void bookSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception;

  /**
   * This method is used by the user to remove himself from a specific sit into
   * the study room. In the case which the day is not into the map, it will be
   * anything.
   *
   * @param day
   *          to search.
   * @param sit
   *          required to cancel.
   * @param userId
   *          of the user.
   * @throws Exception
   *           in the case which the sit is a number < 0 || >= StudyRoom.N or if
   *           the sit is not busy by the specific userId.
   */
  void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception;

  /**
   * This method return the system password for the 'mediatica' manager.
   *
   * @return system password.
   */
  String getSystemPassword();

  /**
   * @param systemPassword
   *          the systemPassword to set
   */
  void setSystemPassword(String systemPassword);
}
