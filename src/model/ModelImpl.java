package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
import model.item.Item;
import model.item.ItemFactory;
import model.item.ItemGenre;
import model.item.Language;
import model.item.ReviewImpl;
import model.user.ArchiveUser;
import model.user.User;

/**
 * Main class of the model. It is Serializable and it has two main field, the
 * first to save item and its info and the second to save the user and its info.
 *
 * @author Edoardo
 *
 */
public class ModelImpl implements Serializable, Model {

  private static final long serialVersionUID = -8370710936091204583L;
  private ArchiveImpl archiveItem = ArchiveImpl.getArchiveImpl();
  private ArchiveUser archiveUser = ArchiveUser.getArchiveImpl();
  private StudyRoom studyRoom = new StudyRoom();
  private String systemPassword = "FmAlchemist";

  @Override
  public void registerUser(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
                          throws Exception {
    User u = new User(initName, initSurname, initBirthdate, initUsername, initPassword, initEmail,
                initTelephoneNumber, initBookPref, initMoviePref);
    this.archiveUser.addUser(u);
    this.setReccomandedList(u.getIdUser());

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
    this.archiveItem.addItem(ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor,
                initCurrentLanguage, initISBN, initGenre, initPublisher, initNumRelease),
                initNumCopy);

  }

  @Override
  public void registerMovie(final String initTitle, final int initReleaseYear,
              final String initPublisher, final String initAuthor,
              final Language initCurrentLanguage, final ItemGenre initGenre,
              final Integer initDuration, final Boolean initColor, final Integer initNumCopy)
                          throws Exception {
    this.archiveItem.addItem(ItemFactory.getNewMovie(initTitle, initReleaseYear, initPublisher,
                initAuthor, initCurrentLanguage, initGenre, initDuration, initColor), initNumCopy);
  }

  @Override
  public void deleteItem(final int itemId) throws Exception {
    if (this.archiveItem.contains(itemId)) {
      this.archiveItem.removeItem(itemId);
    } else {
      throw new Exception("Item: " + itemId + " is not into the archive.");
    }

  }

  @Override
  public void borrowItem(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
      this.archiveItem.addUser(itemId, userId);
      this.archiveUser.getUser(userId).addItem(itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }

  }

  @Override
  public void returnItem(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
      this.archiveItem.removeUser(itemId, userId);
      this.archiveUser.getUser(userId).removeItem(itemId);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive");
    }

  }

  @Override
  public void addLike(final int itemId, final int userId) throws Exception {
    if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
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
    if (this.archiveUser.contains(userId) && this.archiveItem.contains(itemId)) {
      this.archiveUser.getUser(userId).setItemReview(itemId, (int) rev.getId());
      this.archiveItem.getItem(itemId).addReview(rev);
    } else {
      throw new Exception("ItemId: " + itemId + " or userId" + userId
                  + "are not contained into the archive\n");
    }
  }

  @Override
  public Item getRequiredItem(final Integer itemId) throws Exception {
    if (this.archiveItem.contains(itemId)) {
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
    if (this.archiveItem.contains(userId)) {
      for (Integer i : this.archiveUser.getUser(userId).getLoanArchive().keySet()) {
        if (!this.archiveUser.getUser(userId).getLoanArchive().get(i).getFirst()) {
          mmap.put(i, this.archiveItem.calculateDifferenceDays(i, userId));
        }
      }
      return mmap;
    } else {
      throw new Exception("UserId: " + userId + "is not in the archive.");
    }
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
  public Set<Integer> filterItemName(final String name) throws Exception {
    Set<Integer> all = new HashSet<>();
    Set<Integer> r = new HashSet<>();
    all.addAll(this.getAllItemId(TypeItem.BOOK));
    all.addAll(this.getAllItemId(TypeItem.MOVIE));
    for (Integer i : all) {
      if (this.archiveItem.getItem(i).getTitle().equals(name)) {
        r.add(i);
      }
    }
    return r;
  }

  private void setReccomandedList(final Integer userId) {
    List<ItemGenre> prefGenMovie = new LinkedList<>();
    List<ItemGenre> prefGenBook = new LinkedList<>();
    try {
      prefGenMovie = this.archiveUser.getUser(userId).getMoviePreferences();
      prefGenBook = this.archiveUser.getUser(userId).getBookPreferences();
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (ItemGenre i : prefGenMovie) {
      try {
        Set<Integer> all = new HashSet<>(this.filterItemGenre(TypeItem.MOVIE, i));
        Integer start = 0;
        Integer best = 0;
        for (Integer v : all) {
          if (this.archiveItem.getItem(v).getAverageVote() > start) {
            best = v;
          }
        }
        this.archiveUser.getUser(userId).getRecommendedList().add(best);
      } catch (Exception e) {

        e.printStackTrace();
      }
      for (ItemGenre ig : prefGenBook) {
        try {
          Set<Integer> all = new HashSet<>(this.filterItemGenre(TypeItem.BOOK, ig));
          Integer start = 0;
          Integer best = 0;
          for (Integer v : all) {
            if (this.archiveItem.getItem(v).getAverageVote() > start) {
              best = v;
            }
          }
          this.archiveUser.getUser(userId).getRecommendedList().add(best);
        } catch (Exception e) {

          e.printStackTrace();
        }
      }
    }
  }

  @Override
  public void refreshRecommendedList() {
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
    return this.systemPassword;
  }

  @Override
  public void setSystemPassword(final String initSystemPassword) {
    this.systemPassword = initSystemPassword;
  }

}
