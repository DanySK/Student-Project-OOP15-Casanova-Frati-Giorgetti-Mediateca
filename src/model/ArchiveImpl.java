package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class implements Serializable and Archive. This class is the 'real
 * database' of the project. It contains two complex field which are able to
 * save a lot of information about book and movie in the media-center.
 *
 * @author Edoardo
 *
 */
public class ArchiveImpl implements Serializable, Archive {

    /**
     * SSINDD is the double used to divide milliseconds and get the days.
     */
    public static final double SSINDD = 86400000.0;
    private static final long serialVersionUID = 3943672353334594237L;
    private Map<Integer, Pair<ItemImpl, ItemInfo>> itemArchive = new HashMap<>();

    /**
     * Empty constructor. NON COMPLETO DEVE ESSERE AGGIUNTO IL CASO IN CUI
     * L'ARCHIVIO HA GIA' UN FILE DI CONFIGURAZIONE.
     */
    public ArchiveImpl() {
    }

    @Override
    public void addItem(final ItemImpl i, final Integer initNumCopy) throws Exception {
        if (initNumCopy > 0) {
            if (!this.itemArchive.containsKey(i.getiD())) {
                this.itemArchive.put(i.getiD(), new Pair<>(i, new ItemInfo(initNumCopy)));
            } else {
                this.changeAmount(i.getiD(), initNumCopy);
            }
        } else {
            throw new Exception("initNumCopy <= 0");
        }
    }

    @Override
    public void changeAmount(final Integer itemId, final Integer amount) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            this.itemArchive.get(itemId).getSecond().addQuantity(amount);
        } else {
            throw new Exception("Item " + itemId + " is not in the archive.");
        }

    }

    @Override
    public Item getItem(final Integer itemId) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            if (Book.class.isInstance(this.itemArchive.get(itemId).getFirst())) {
                return this.itemArchive.get(itemId).getFirst();
            }
            if (Movie.class.isInstance(this.itemArchive.get(itemId).getFirst())) {
                return this.itemArchive.get(itemId).getFirst();
            }
        } else {
            throw new Exception("Item " + itemId + "is not in the archive.");
        }
        return null;
    }

    @Override
    public void removeItem(final Integer itemId) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            this.itemArchive.remove(itemId);
        } else {
            throw new Exception("Item: " + itemId + " is not into the archive.");
        }

    }

    @Override
    public double calculateDifferenceDays(final Integer itemId, final Integer userId) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            if (this.itemArchive.get(itemId).getSecond().getUserList().containsKey(userId)) {
                return this.dayBetweenDates(this.itemArchive.get(itemId).getSecond().getUserList().get(userId));
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
        if (this.itemArchive.containsKey(itemId)) {
            this.itemArchive.get(itemId).getSecond().getUserList().put(userId, this.getToDay());
            System.out.println("User " + userId + " adds to book list " + itemId + " in date " + this.getToDay());
        } else {
            throw new Exception("User: " + userId + "can not take item: " + itemId
                    + "becouse it is not contained into the archive.");
        }
    }

    @Override
    public void removeUser(final Integer itemId, final Integer userId) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            if (this.itemArchive.get(itemId).getSecond().getUserList().containsKey(userId)) {
                this.itemArchive.get(itemId).getSecond().getUserList().remove(userId, this.getToDay());
                System.out.println("User " + userId + " adds to item list " + itemId + " in date " + this.getToDay());
            } else {
                throw new Exception(("userId is not in the item's list"));
            }
        } else {
            throw new Exception(
                    "User: " + userId + "can not remove user:" + userId + " from item: " + itemId + "list.");
        }
    }

    @Override
    public boolean checkAvailability(final Integer itemId) throws Exception {
        if (this.itemArchive.containsKey(itemId)) {
            return this.itemArchive.get(itemId).getSecond().isAvailable();
        } else {
            throw new Exception("Item: " + itemId + "Not contained into the archive.");
        }
    }

    @Override
    public List<Integer> getUserList(final Type t, final Integer itemId) throws Exception { // OK
        LinkedList<Integer> ls = new LinkedList<>();
        if (t == Type.BOOK) {
            if (this.bookArchive.isEmpty()) {
                return ls;
            }
            if (this.bookArchive.containsKey(itemId)) {
                for (int i = 0; i < this.bookArchive.get(itemId).getSecond().getSecond().size(); i++) {
                    ls.add(this.bookArchive.get(itemId).getSecond().getSecond().get(i).getFirst());
                }
                return ls;
            } else {
                throw new Exception("Book: " + itemId + "Not contained into the archive.");
            }
        }
        if (t == Type.MOVIE) {
            if (this.movieArchive.isEmpty()) {
                return ls;
            }
            if (this.movieArchive.containsKey(itemId)) {
                for (int i = 0; i < this.movieArchive.get(itemId).getSecond().getSecond().size(); i++) {
                    ls.add(this.movieArchive.get(itemId).getSecond().getSecond().get(i).getFirst());
                }
                return ls;
            } else {
                throw new Exception("Movie: " + itemId + " Not contained into the archive.");
            }
        }
        if ((t != Type.BOOK) && (t != Type.MOVIE)) {
            throw new RuntimeException("Error Type.");
        }
        return ls;
    }
}
