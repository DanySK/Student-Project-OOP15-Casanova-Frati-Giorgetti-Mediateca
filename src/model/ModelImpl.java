package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
import model.item.Item;
import model.item.ItemFactory;
import model.item.ItemGenre;
import model.item.ItemImpl;
import model.item.ItemInfo;
import model.item.Language;
import model.item.ReviewImpl;
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
  private ArchiveImpl archiveItem = ArchiveImpl.getArchiveImpl();
  private ArchiveUserImpl archiveUser = ArchiveUserImpl.getArchiveImpl();
  private StudyRoom studyRoom = new StudyRoom();
  private String systemPassword = "FmAlchemist";

  /**
   * Empty constructor.
   */
  public ModelImpl() {
  }

  /**
   * Enum in order to simplify filter.
   *
   */
  public enum TypeSearch {
    /**
     *
     */
    TITLE, AUTHOR, PUBLISHER, RELEASE_YEAR
  }

  @Override
  public Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive() {
    return this.archiveItem.getItemArchive();
  }

  @Override
  public void setItemArchive(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive)
              throws Exception {
    this.archiveItem.setArchiveImpl(initItemArchive);
  }

  @Override
  public Map<Integer, UserImpl> getUserArchive() {
    return this.archiveUser.getUserArchive();
  }

  @Override
  public void setUserArchive(final Map<Integer, UserImpl> initArchiveUser) throws Exception {
    this.archiveUser.setArchiveImpl(initArchiveUser);
  }

  @Override
  public void registerUser(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
                          throws Exception {
    UserImpl u = new UserImpl(initName, initSurname, initBirthdate, initUsername, initPassword,
                initEmail, initTelephoneNumber, initBookPref, initMoviePref);
    this.archiveUser.addUser(u);
    this.setReccomandedList(u.getIdUser());
    System.out.println("User " + u.toString() + "adds to te archive.");
  }

  @Override
  public void deleteUser(final int userId) throws Exception {
    if (this.archiveUser.contains(userId)) {
      this.archiveUser.removeUser(userId);
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }

  }

  @Override
  public void registerBook(final String initTitle, final int initReleaseYear,
              final String initAuthor, final Language initCurrentLanguage, final String initISBN,
              final ItemGenre initGenre, final String initPublisher, final Integer initNumRelease,
              final Integer initNumCopy) throws Exception {
    // Da sistemare prima della consegna, Item Facotry direttamente dentro il
    // metodo addItem
    ItemImpl b = ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor, initCurrentLanguage,
                initISBN, initGenre, initPublisher, initNumRelease);
    this.archiveItem.addItem(b, initNumCopy);
    System.out.println("Book " + b.toString() + " adds to the archive");

  }

  @Override
  public void registerMovie(final String initTitle, final int initReleaseYear,
              final String initPublisher, final String initAuthor,
              final Language initCurrentLanguage, final ItemGenre initGenre,
              final Integer initDuration, final Boolean initColor, final Integer initNumCopy)
                          throws Exception {
    // Da sistemare prima della consegna, Item Facotry direttamente dentro il
    // metodo addItem
    ItemImpl m = ItemFactory.getNewMovie(initTitle, initReleaseYear, initPublisher, initAuthor,
                initCurrentLanguage, initGenre, initDuration, initColor);
    this.archiveItem.addItem(m, initNumCopy);
    System.out.println("Movie " + m.toString() + " adds to the archive");
  }

  @Override
  public void deleteItem(final int itemId) throws Exception {
    if (this.archiveItem.containsItem(itemId)) {
      this.archiveItem.removeItem(itemId);
    } else {
      throw new Exception("Item: " + itemId + " is not into the archive.");
    }

  }

  @Override
  public void borrowItem(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.containsItem(itemId) && this.archiveUser.contains(userId)) {
      this.archiveItem.addUser(itemId, userId);
      this.archiveUser.getUser(userId).addItem(itemId);
      System.out.println("UserId " + userId + "takes itemId " + itemId);
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
      this.archiveUser.getUser(userId).setItemReview(itemId, (int) rev.getId());
      this.archiveItem.getItem(itemId).addReview(rev);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive\n");
    }
  }

  @Override
  public List<ReviewImpl> getAllItemReview(final Integer itemId) throws Exception {
    return (List<ReviewImpl>) Collections
                .unmodifiableCollection(this.archiveItem.getItem(itemId).getSetReview());
  }

  @Override
  public Item getRequiredItem(final Integer itemId) throws Exception {
    if (this.archiveItem.containsItem(itemId)) {
      return this.archiveItem.getItem(itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " not contained into the archive\n");
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
  public Set<Integer> filterItemGenre(final TypeItem t, final ItemGenre b) throws Exception {
    Set<Integer> r = new HashSet<>();
    Set<Integer> all = new HashSet<>();
    if (t == TypeItem.BOOK) {
      all = this.getAllItemId(TypeItem.BOOK);
    }
    if (t == TypeItem.MOVIE) {
      all = this.getAllItemId(TypeItem.MOVIE);
    }
    if ((t != TypeItem.MOVIE) && (t != TypeItem.BOOK)) {
      throw new Exception("TypeItem " + t + "not valid");
    }
    for (Integer i : all) {
      if (this.archiveItem.getItem(i).getGenre().equals(b)) {
        r.add(i);
      }
    }
    return r;
  }

  @Override
  public Set<Integer> filterItem(final Optional<Set<Integer>> set, final TypeSearch ts,
              final String param) throws Exception {
    if ((ts != TypeSearch.AUTHOR) || (ts != TypeSearch.TITLE) || (ts != TypeSearch.PUBLISHER)
                || (ts != TypeSearch.RELEASE_YEAR)) {
      throw new Exception("TypeSearch " + ts + "not valid");
    }
    Set<Integer> all = new HashSet<>();
    if (set.isPresent()) {
      all.addAll(set.get());
    }
    Set<Integer> r = new HashSet<>();
    all.addAll(this.getAllItemId(TypeItem.BOOK));
    all.addAll(this.getAllItemId(TypeItem.MOVIE));
    for (Integer i : all) {
      if (ts == TypeSearch.TITLE) {
        if (this.archiveItem.getItem(i).getTitle().equals(param)) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.AUTHOR) {
        if (this.archiveItem.getItem(i).getAuthor().equals(param)) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.PUBLISHER) {
        if (this.archiveItem.getItem(i).getPublisher().equals(param)) {
          r.add(i);
        }
      }
      if (ts == TypeSearch.RELEASE_YEAR) {
        Integer num = Integer.parseInt(param);
        if (num.equals(this.archiveItem.getItem(i).getReleaseYear())) {
          r.add(i);
        }
      }
    }
    return r;
  }

  private void setReccomandedList(final Integer userId) throws Exception {
    List<ItemGenre> prefGenMovie = new LinkedList<>(
                this.archiveUser.getUser(userId).getMoviePreferences());
    List<ItemGenre> prefGenBook = new LinkedList<>(
                this.archiveUser.getUser(userId).getBookPreferences());
    Set<Integer> all;

    for (ItemGenre im : prefGenMovie) {
      all = this.filterItemGenre(TypeItem.MOVIE, im);
      if (all.size() != 0) {
        Integer start = 0;
        Integer best = 0;
        for (Integer v : all) {
          if (this.archiveItem.getItem(v).getAverageVote() > start) {
            best = v;
          }
        }
        this.archiveUser.getUser(userId).getRecommendedList().add(best);
      }
    }

    for (ItemGenre ig : prefGenBook) {
      all = this.filterItemGenre(TypeItem.BOOK, ig);
      if (all.size() != 0) {
        Integer start = 0;
        Integer best = 0;
        for (Integer v : all) {
          if (this.archiveItem.getItem(v).getAverageVote() > start) {
            best = v;
          }
        }
        this.archiveUser.getUser(userId).getRecommendedList().add(best);
      }
    }
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
    this.studyRoom.takeSit(initDay, initSit, initUserId);
  }

  @Override
  public void cancelSit(final GregorianCalendar day, final Integer sit, final Integer userId)
              throws Exception {
    this.studyRoom.cancelSit(day, sit, userId);
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

}
