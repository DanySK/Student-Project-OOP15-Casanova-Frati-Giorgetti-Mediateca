package model.item;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Pair;

/**
 * This class implements Serializable and Archive. This class is the 'real
 * database' of the project. It contains one field which is able to save a lot
 * of information about book and movie into 'Mediateca'.
 *
 * @author Edoardo
 *
 */
public final class ArchiveImpl implements Serializable, Archive {

  /**
   * SSINDD is the double used to divide milliseconds and get the days.
   */
  private static final double SSINDD = 86400000.0;
  private static final long serialVersionUID = 3943672353334594237L;
  private static ArchiveImpl singleton = null;
  private Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive = new HashMap<>();

  /**
   * Simple enum in order to know if item is a book or a movie.
   *
   * @author Edoardo
   *
   */
  public enum TypeItem {
    /**
     * Item's types.
     */
    MOVIE, BOOK
  }

  private ArchiveImpl() {
  }

  /**
   * Singleton constructor.
   *
   * @return the ArchiveImpl.
   */
  public static ArchiveImpl getArchiveImpl() {
    if (ArchiveImpl.singleton == null) {
      ArchiveImpl.singleton = new ArchiveImpl();
    }
    return ArchiveImpl.singleton;
  }

  @Override
  public void setArchiveImpl(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive)
              throws Exception {
    if (ArchiveImpl.singleton == null) {
      ArchiveImpl.getArchiveImpl().setItemArchive(initItemArchive);
    } else {
      throw new Exception("Item archive already loaded");
    }
  }

  /**
   * @return the itemArchive
   */
  public Map<Integer, Pair<ItemImpl, ItemInfo>> getItemArchive() {
    return this.itemArchive;
  }

  /**
   * @param initItemArchive
   *          the itemArchive to set
   */
  public void setItemArchive(final Map<Integer, Pair<ItemImpl, ItemInfo>> initItemArchive) {
    this.itemArchive = initItemArchive;
  }

  @Override
  public void addItem(final ItemImpl i, final Integer initNumCopy) throws Exception {
    if (initNumCopy > 0) {
      if (!this.containsItem(i.getiD())) {
        ArchiveImpl.singleton.getItemArchive().put(i.getiD(),
                    new Pair<>(i, new ItemInfo(initNumCopy)));
      } else {
        ArchiveImpl.singleton.calculateDifferenceDays(i.getiD(), initNumCopy);
      }
    } else {
      throw new RuntimeException("initNumCopy <= 0");
    }
  }

  @Override
  public void changeAmount(final Integer itemId, final Integer amount) throws Exception {
    if (this.containsItem(itemId)) {
      ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().addQuantity(amount);
    } else {
      throw new Exception("Item " + itemId + " is not in the archive.");
    }

  }

  @Override
  public Item getItem(final Integer itemId) throws Exception {
    if (this.containsItem(itemId)) {
      return ArchiveImpl.singleton.getItemArchive().get(itemId).getFirst();
    } else {
      throw new Exception("Item " + itemId + "is not in the archive.");
    }
  }

  @Override
  public void removeItem(final Integer itemId) throws Exception {
    if (this.containsItem(itemId)) {
      ArchiveImpl.singleton.getItemArchive().remove(itemId);
    } else {
      throw new Exception("Item: " + itemId + " is not into the archive.");
    }

  }

  @Override
  public double calculateDifferenceDays(final Integer itemId, final Integer userId)
              throws Exception {
    if (this.containsItem(itemId)) {
      if (ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().getUserList()
                  .containsKey(userId)) {
        return this.dayBetweenDates(ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond()
                    .getUserList().get(userId));
      } else {
        throw new Exception("User: " + userId + "Not contained into the " + itemId + " list");
      }
    } else {
      throw new Exception("Item: " + itemId + "Not contained into the archive.");
    }
  }

  private double dayBetweenDates(final GregorianCalendar fromDate) {

    GregorianCalendar toDate = this.getToDay();

    // Conversion in ms
    long msFromDate = fromDate.getTimeInMillis();
    long msToDate = toDate.getTimeInMillis();

    long msBetween = msToDate - msFromDate;

    // Conversion in days with math rounding
    double ddBetween = Math.round(msBetween / ArchiveImpl.SSINDD);

    return ddBetween;
  }

  private GregorianCalendar getToDay() {
    // Current date
    Calendar c = Calendar.getInstance();
    GregorianCalendar toDay = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
    return toDay;
  }

  @Override
  public void addUser(final Integer itemId, final Integer userId) throws Exception {
    if (this.containsItem(itemId)) {
      ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().getUserList().put(userId,
                  this.getToDay());
      System.out.println("User " + userId + " adds to book list " + itemId + " in date "
                  + this.getToDay());
    } else {
      throw new Exception("User: " + userId + "can not take item: " + itemId
                  + "becouse it is not contained into the archive.");
    }
  }

  @Override
  public void removeUser(final Integer itemId, final Integer userId) throws Exception {
    if (this.containsItem(itemId)) {
      if (ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().getUserList()
                  .containsKey(userId)) {
        ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().getUserList().remove(userId,
                    this.getToDay());
        System.out.println("User " + userId + " adds to item list " + itemId + " in date "
                    + this.getToDay());
      } else {
        throw new Exception(("userId is not in the item's list"));
      }
    } else {
      throw new Exception("User: " + userId + "can not remove user:" + userId + " from item: "
                  + itemId + "list.");
    }
  }

  @Override
  public boolean checkAvailability(final Integer itemId) throws Exception {
    if (this.containsItem(itemId)) {
      return ArchiveImpl.singleton.getItemArchive().get(itemId).getSecond().isAvailable();
    } else {
      throw new Exception("Item: " + itemId + "Not contained into the archive.");
    }
  }

  @Override
  public Set<Integer> getUserList(final Integer itemId) throws Exception {
    if (this.containsItem(itemId)) {
      return Collections.unmodifiableSet(ArchiveImpl.singleton.getItemArchive().get(itemId)
                  .getSecond().getUserList().keySet());
    } else {
      throw new Exception("Item: " + itemId + "Not contained into the archive.");
    }
  }

  @Override
  public boolean containsItem(final Integer itemId) {
    return (ArchiveImpl.singleton.getItemArchive().containsKey(itemId));
  }

  @Override
  public Set<Integer> getItemId(final TypeItem t) {
    Set<Integer> booklist = new HashSet<>();
    Set<Integer> movielist = new HashSet<>();
    for (Pair<ItemImpl, ItemInfo> i : ArchiveImpl.singleton.getItemArchive().values()) {
      if (Book.class.isInstance(i.getFirst())) {
        booklist.add(i.getFirst().getiD());
      }
      if (Movie.class.isInstance(i.getFirst())) {
        movielist.add(i.getFirst().getiD());
      }
    }
    if (t == TypeItem.BOOK) {
      return Collections.unmodifiableSet(booklist);
    }
    if (t == TypeItem.MOVIE) {
      return Collections.unmodifiableSet(movielist);
    }
    return null;
  }
}
