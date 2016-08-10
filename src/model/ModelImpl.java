package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.item.Archive;
import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
import model.item.ItemFactory;
import model.item.ItemGenre;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.Language;
import model.item.ReviewImpl;
import model.user.ArchiveUser;
import model.user.ArchiveUserImpl;
import model.user.UserImpl;

/**
 * Main class of the model. It is Serializable and it has two main field, the
 * first to save item and its info and the second to save the user and its info.
 *
 * @author Edoardo
 *
 */
public class ModelImpl implements Serializable, Model {

  private static final long serialVersionUID = -8370710936091204583L;
  private static final int MAX_DAY = 60;
  private Archive archiveItem;
  private ArchiveUser archiveUser;
  private StudyRoom studyRoom;
  private String systemPassword = "FmAlchemist";

  /**
   * Empty constructor.
   */
  public ModelImpl() {
    this.archiveItem = ArchiveImpl.getArchiveImpl();
    this.archiveUser = ArchiveUserImpl.getArchiveImpl();
    this.studyRoom = new StudyRoomImpl();
  }

  /**
   * Constructor that must be used in the case which there is already archive
   * files saved.
   *
   * @param initItemArchive
   *          Item archive saved in the file in order to be deserialized.
   * @param initArchiveUser
   *          User archive saved in the file in order to be deserialized.
   * @param initStudyRoom
   *          StudyRoom saved in the file in order to be deserialized.
   *
   * @throws Exception
   *           in the case which singleton already exist.
   */
  public ModelImpl(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive,
              final Map<Integer, UserImpl> initArchiveUser,
              final Map<GregorianCalendar, ArrayList<Integer>> initStudyRoom) throws Exception {
    this.archiveItem = ArchiveImpl.getArchiveImpl();
    this.archiveUser = ArchiveUserImpl.getArchiveImpl();
    this.setItemArchive(initItemArchive);
    this.setUserArchive(initArchiveUser);
    this.studyRoom = new StudyRoomImpl(initStudyRoom);
  }

  /**
   * Enum in order to simplify filter.
   *
   */
  public enum TypeSearch {
    /**
     *
     */
    TITLE, AUTHOR, PUBLISHER, RELEASE_YEAR, LANGUAGE, GENRE, NAME, SURNAME, BIRTHDATE, USERNAME, PASSWORD, EMAIL, TELEPHONE_NUMBER
  }

  @Override
  public Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive() {
    return this.archiveItem.getItemArchive();
  }

  /**
   * This method can be used by MANAGER. This method set the item archive in
   * order to be (de)serialized.
   *
   * @param itemArchive
   *          item's archive.
   * @throws Exception
   *           in the case which the archive is been already initialized.
   */
  private void setItemArchive(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive)
              throws Exception {
    this.archiveItem.setArchiveItemImpl(initItemArchive);
  }

  @Override
  public Map<Integer, UserImpl> getUserArchive() {
    return this.archiveUser.getUserArchive();
  }

  /**
   * This method can be used by MANAGER.This method set the user archive in
   * order to be (de)serialized.
   *
   * @param initArchiveUser
   *          the archiveUser to set
   *
   * @throws Exception
   *           in the case which the archive is benne already initialized.
   */
  private void setUserArchive(final Map<Integer, UserImpl> initArchiveUser) throws Exception {
    this.archiveUser.setArchiveUserImpl(initArchiveUser);
  }

  @Override
  public void registerUser(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
              throws Exception {
    if (!this.checkUsername(initUsername)) {
      UserImpl u = new UserImpl(initName, initSurname, initBirthdate, initUsername, initPassword,
                  initEmail, initTelephoneNumber, initBookPref, initMoviePref);
      this.archiveUser.addUser(u);
      this.setReccomandedList(u.getIdUser());
      System.out.println("User " + u.toString() + "adds to te archive.");
    } else {
      throw new Exception("Can not add user " + initUsername
                  + " into the archive becouse there is already a user with that username.");
    }
  }

  /**
   *
   * @param initUsername
   *          username to find in the archive.
   * @return true if username is in the archive, false if username is not in the
   *         archive.
   */
  private boolean checkUsername(final String initUsername) {
    return this.archiveUser.getAllUsername().contains(initUsername);
  }

  @Override
  public void deleteUser(final int userId) throws Exception {
    if (this.archiveUser.contains(userId)) {
      this.archiveUser.removeUser(userId);
      System.out.println("User: " + userId + " removed from archive.");
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }

  }

  @Override
  public void registerBook(final String initTitle, final int initReleaseYear,
              final String initAuthor, final Language initCurrentLanguage, final String initISBN,
              final ItemGenre initGenre, final String initPublisher, final Integer initNumRelease,
              final Integer initNumCopy) throws Exception {

    ItemImpl b = ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor, initCurrentLanguage,
                initISBN, initGenre, initPublisher, initNumRelease);
    if (!this.archiveItem.containsItem(b.getiD())) {
      this.archiveItem.addItem(b, initNumCopy);
      System.out.println("Book " + b.toString() + " adds to the archive");
    } else {
      this.archiveItem.changeAmount(b.getiD(), initNumCopy);
      System.out.println("Item already present in the archive, change amount in "
                  + this.archiveItem.getItemArchive().get(b.getiD()).getSecond().getQuantity());
    }
  }

  @Override
  public void registerMovie(final String initTitle, final int initReleaseYear,
              final String initPublisher, final String initAuthor,
              final Language initCurrentLanguage, final ItemGenre initGenre,
              final Integer initDuration, final Boolean initColor, final Integer initNumCopy)
              throws Exception {
    ItemImpl m = ItemFactory.getNewMovie(initTitle, initReleaseYear, initPublisher, initAuthor,
                initCurrentLanguage, initGenre, initDuration, initColor);
    if (!this.archiveItem.containsItem(m.getiD())) {
      this.archiveItem.addItem(m, initNumCopy);
      System.out.println("Movie " + m.toString() + " adds to the archive");
    } else {
      this.archiveItem.changeAmount(m.getiD(), initNumCopy);
      System.out.println("Item already present in the archive, change amount in "
                  + this.archiveItem.getItemArchive().get(m.getiD()).getSecond().getQuantity());
    }
  }

  @Override
  public void deleteItem(final int itemId) throws Exception {
    if (this.archiveItem.containsItem(itemId)) {
      this.archiveItem.removeItem(itemId);
      System.out.println("Item: " + itemId + " removed from archive.");
    } else {
      throw new Exception("Item: " + itemId + " is not into the archive.");
    }

  }

  @Override
  public void borrowItem(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      if (this.archiveItem.getItemInfo(itemId).isAvailable()) {
        this.archiveItem.addUser(itemId, userId);
        this.archiveUser.getUser(userId).addItem(itemId);
        System.out.println("UserId " + userId + "takes itemId " + itemId);
      } else {
        System.out.println(itemId + " not available.");
      }
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }

  }

  @Override
  public void returnItem(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      this.archiveItem.removeUser(itemId, userId);
      this.archiveUser.getUser(userId).removeItem(itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }

  }

  @Override
  public void addLike(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      this.archiveItem.getItem(itemId).addLike(userId);
      this.archiveUser.getUser(userId).addToWishList(itemId);
      System.out.println("UserId: " + userId + " likes itemId: " + itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }
  }

  @Override
  public void addReview(final Integer itemId, final Integer userId, final Integer vote,
              final String note) throws Exception {
    ReviewImpl rev = new ReviewImpl(vote, note);
    if (this.archiveUser.contains(userId) && this.archiveItem.containsItem(itemId)) {

      if (this.getRequiredUser(userId).getLoanArchive().containsKey(itemId)) {
        System.out.println("Qui ci sono");
        this.getRequiredUser(userId).setItemReview(itemId, (int) rev.getId());
        this.archiveItem.getItem(itemId).addReview(rev);
        System.out.println(rev.toString() + " adds.");
      } else {
        throw new Exception("ItemId: " + itemId + " not loaned to " + userId + " userId\n");
      }
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive\n");
    }
  }

  @Override
  public List<ReviewImpl> getAllItemReview(final Integer itemId) throws Exception {
    return (List<ReviewImpl>) Collections.unmodifiableCollection(
                ((ItemImpl) this.archiveItem.getItem(itemId)).getSetReview());
  }

  @Override
  public ItemImpl getRequiredItem(final Integer itemId) throws Exception {
    if (this.archiveItem.containsItem(itemId)) {
      return (ItemImpl) this.archiveItem.getItem(itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " not contained into the archive\n");
    }
  }

  @Override
  public UserImpl getRequiredUser(final Integer userId) throws Exception {
    if (this.archiveUser.contains(userId)) {
      return this.archiveUser.getUser(userId);
    } else {
      throw new Exception("UserId: " + userId + " not contained into the archive\n");
    }
  }

  @Override
  public Set<Integer> getAllItemId(final TypeItem t) {
    return this.archiveItem.getItemId(t);
  }

  @Override
  public Set<Integer> getAllUserId() {
    return this.archiveUser.getUserId();
  }

  @Override
  public Map<Integer, Double> checkDeadlineas(final Integer userId) throws Exception {
    Map<Integer, Double> mmap = new HashMap<>();
    if (this.archiveItem.containsItem(userId)) {
      for (Integer i : this.archiveUser.getUser(userId).getLoanArchive().keySet()) {
        if (!this.archiveUser.getUser(userId).itWasReturned(i)) {
          mmap.put(i, this.archiveItem.calculateDifferenceDays(i, userId));
        }
      }
      return mmap;
    } else {
      throw new Exception("UserId: " + userId + "is not in the archive.");
    }
  }

  @Override
  public boolean blockUser(final Integer userId) throws Exception {
    Map<Integer, Double> mmap = this.checkDeadlineas(userId);
    for (Double d : mmap.values()) {
      if (d > ModelImpl.MAX_DAY) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Set<Integer> filtersItem(final Set<Integer> set, final TypeSearch ts, final Object param)
              throws Exception {
    if ((ts != TypeSearch.AUTHOR) && (ts != TypeSearch.TITLE) && (ts != TypeSearch.PUBLISHER)
                && (ts != TypeSearch.RELEASE_YEAR) && (ts != TypeSearch.LANGUAGE)
                && (ts != TypeSearch.GENRE)) {
      throw new Exception("TypeSearch " + ts + "not valid");
    }
    Set<Integer> r = new HashSet<>();
    for (Integer i : set) {
      if (ts == TypeSearch.TITLE) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getTitle()
                    .equals(((String) param).toUpperCase())) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.AUTHOR) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getAuthor()
                    .equals(((String) param).toUpperCase())) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.PUBLISHER) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getPublisher()
                    .equals(((String) param).toUpperCase())) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.RELEASE_YEAR) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getReleaseYear() == (int) param) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.LANGUAGE) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getCurrentLanguage().equals(param)) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.GENRE) {
        if (((ItemImpl) this.archiveItem.getItem(i)).getGenre().equals(param)) {
          r.add(i);
        }
      }
    }
    return r;
  }

  @Override
  public void changeItem(final TypeSearch ts, final Integer itemId, final Object param)
              throws Exception {
    if ((ts != TypeSearch.AUTHOR) && (ts != TypeSearch.TITLE) && (ts != TypeSearch.PUBLISHER)
                && (ts != TypeSearch.RELEASE_YEAR) && (ts != TypeSearch.LANGUAGE)
                && (ts != TypeSearch.GENRE)) {
      throw new Exception("TypeSearch " + ts + "not valid to change on item");
    }

    if (ts == TypeSearch.TITLE) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setTitle((String) param);
    }
    if (ts == TypeSearch.AUTHOR) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setAuthor((String) param);
    }
    if (ts == TypeSearch.PUBLISHER) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setPublisher((String) param);
    }
    if (ts == TypeSearch.RELEASE_YEAR) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setReleaseYear((int) param);
    }
    if (ts == TypeSearch.LANGUAGE) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setCurrentLanguage((Language) param);
    }
    if (ts == TypeSearch.GENRE) {
      ((ItemImpl) this.archiveItem.getItem(itemId)).setGenre((ItemGenre) param);
    }
  }

  @Override
  public void changeUser(final TypeSearch ts, final Integer userId, final Object param)
              throws Exception {
    if ((ts != TypeSearch.NAME) && (ts != TypeSearch.SURNAME) && (ts != TypeSearch.BIRTHDATE)
                && (ts != TypeSearch.USERNAME) && (ts != TypeSearch.PASSWORD)
                && (ts != TypeSearch.EMAIL) && (ts != TypeSearch.TELEPHONE_NUMBER)) {
      throw new Exception("TypeSearch " + ts + "not valid to change on User");
    }
    if (ts == TypeSearch.NAME) {
      this.archiveUser.getUser(userId).setName((String) param);
    }
    if (ts == TypeSearch.SURNAME) {
      this.archiveUser.getUser(userId).setSurname((String) param);
    }

    if (ts == TypeSearch.BIRTHDATE) {
      this.archiveUser.getUser(userId).setBirthdate((GregorianCalendar) param);
    }
    if (ts == TypeSearch.USERNAME) {
      this.archiveUser.getUser(userId).setUsername((String) param);
    }
    if (ts == TypeSearch.PASSWORD) {
      this.archiveUser.getUser(userId).setPassword((String) param);
    }
    if (ts == TypeSearch.EMAIL) {
      this.archiveUser.getUser(userId).setEmail((String) param);
    }
    if (ts == TypeSearch.TELEPHONE_NUMBER) {
      this.archiveUser.getUser(userId).setTelephoneNumber((String) param);
    }
  }

  @Override
  public void setReccomandedList(final Integer userId) throws Exception {

    Set<Integer> all;
    List<Integer> toAdd = new LinkedList<Integer>();

    for (ItemGenre im : this.archiveUser.getUser(userId).getMoviePreferences()) {
      all = this.filtersItem(this.getAllItemId(TypeItem.MOVIE), TypeSearch.GENRE, im);
      // all = this.filterItemGenre(TypeItem.MOVIE, im);
      if (all.size() != 0) {
        Integer start = 0;
        Integer best = 0;
        for (Integer v : all) {
          if (((ItemImpl) this.archiveItem.getItem(v)).getAverageVote() >= start) {
            best = v;
          }
        }
        toAdd.add(best);
      }
    }

    for (ItemGenre ig : this.archiveUser.getUser(userId).getBookPreferences()) {
      all = this.filtersItem(this.getAllItemId(TypeItem.BOOK), TypeSearch.GENRE, ig);
      // all = this.filterItemGenre(TypeItem.BOOK, ig);
      if (all.size() != 0) {
        Integer start = 0;
        Integer best = 0;
        for (Integer v : all) {
          if (((ItemImpl) this.archiveItem.getItem(v)).getAverageVote() >= start) {
            best = v;
          }
        }
        toAdd.add(best);
      }
    }
    this.archiveUser.getUser(userId).setRecommendedList(toAdd);

  }

  @Override
  public void refreshRecommendedList() throws Exception {
    for (Integer i : this.getAllUserId()) {
      this.setReccomandedList(i);
    }
  }

  @Override
  public void bookSit(final GregorianCalendar initDay, final Integer initSit,
              final Integer initUserId) throws Exception {
    if (this.archiveUser.contains(initUserId)) {
      this.studyRoom.takeSit(initDay, initSit, initUserId);
    } else {
      throw new Exception("UserId: " + initUserId + " not in the archive.");
    }
  }

  @Override
  public void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception {
    this.studyRoom.cancelSit(day, sit, userId);
  }

  @Override
  public List<Integer> getAllUserSit(final GregorianCalendar day) {
    return this.studyRoom.getAllSit(day);
  }

  @Override
  public String getSystemPassword() {
    String s = this.systemPassword;
    return s;
  }

  @Override
  public void setSystemPassword(final String initSystemPassword) {
    this.systemPassword = initSystemPassword;
  }

  @Override
  public Map<GregorianCalendar, ArrayList<Integer>> getStudyRoom() {
    return this.studyRoom.getStudyRoom();
  }

}
