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
    private Map<Integer, Pair<Book, Pair<Integer, List<Pair<Integer, GregorianCalendar>>>>> bookArchive = new HashMap<>();
    private Map<Integer, Pair<Movie, Pair<Integer, List<Pair<Integer, GregorianCalendar>>>>> movieArchive = new HashMap<>();

    /**
     * Empty constructor. NON COMPLETO DEVE ESSERE AGGIUNTO IL CASO IN CUI
     * L'ARCHIVIO HA GIA' UN FILE DI CONFIGURAZIONE.
     */

    public ArchiveImpl() {
    }

    @Override
    public <X extends ItemImpl> void addItem(final X i, final Integer initNumCopy) {
        if (Book.class.isInstance(i)) {
            if (this.bookArchive.isEmpty()) {
                if (this.bookArchive.containsKey(i.getiD())) {
                    System.out.println(
                            "Book already present in the archive!\nAdding of initNumCopy to te pre-existing value");
                    this.changeAmount(Type.BOOK, i.getiD(), initNumCopy);
                } else {
                    this.bookArchive.put(i.getiD(), new Pair(i, new Pair(initNumCopy, new LinkedList<>())));
                }
            }
        }
        if (Movie.class.isInstance(i)) {
            if (this.movieArchive.containsKey(i.getiD())) {
                System.out.println(
                        "Movie already present in the archive!\nAdding of initNumCopy to te pre-existing value");
                this.changeAmount(Type.MOVIE, i.getiD(), initNumCopy);
            } else {
                this.movieArchive.put(i.getiD(), new Pair(i, new Pair(initNumCopy, new LinkedList<>())));
                System.out.println(i.toString() + "Sono di movie");
            }
        }

    }

    @Override
    public void changeAmount(final Type t, final Integer id, final Integer amount) {
        if (t == Type.BOOK) {
            this.bookArchive.get(id).getSecond().setFirst(this.bookArchive.get(id).getSecond().getFirst() + amount);
        } else if (t == Type.MOVIE) {
            this.movieArchive.get(id).getSecond().setFirst(this.movieArchive.get(id).getSecond().getFirst() + amount);
        }
    }

    @Override
    public Item getItem(final Type t, final Integer id) {
        if (t == Type.BOOK) {
            return this.bookArchive.get(id).getFirst();
        } else if (t == Type.MOVIE) {
            return this.movieArchive.get(id).getFirst();
        } else {
            throw new RuntimeException("Error type.");
        }
    }

    @Override
    public void removeItem(final Type t, final Integer id) {
        if (t == Type.BOOK) {
            this.bookArchive.remove(id);
            System.out.println("Book with id: " + id + "removed.");
        } else if (t == Type.MOVIE) {
            this.movieArchive.remove(id);
            System.out.println("Movie with id: " + id + "removed.");
        } else {
            throw new RuntimeException("Error type.");
        }
    }

    @Override
    public String toString() {
        return "ArchiveImpl [BookArchive=" + this.bookArchive + ", MovieArchive=" + this.movieArchive + "]";
    }

    @Override
    public double calculateDifferenceDays(final Type t, final Integer id, final Integer userId) {
        if (t == Type.BOOK) {
            int i = this.bookArchive.get(id).getSecond().getSecond().indexOf(userId);
            return this.dayBetweenDates(this.bookArchive.get(id).getSecond().getSecond().get(i).getSecond());
        } else if (t == Type.MOVIE) {
            int i = this.movieArchive.get(id).getSecond().getSecond().indexOf(userId);
            return this.dayBetweenDates(this.movieArchive.get(id).getSecond().getSecond().get(i).getSecond());
        } else {
            throw new RuntimeException("Error type.");
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
    public void addUser(final Type t, final Integer itemId, final Integer userId) {
        if (t == Type.BOOK) {
            this.bookArchive.get(itemId).getSecond().getSecond().add(new Pair(userId, this.getToDay()));
            System.out.println("User " + userId + " adds to book list " + itemId + " in date " + this.getToDay());
        } else if (t == Type.MOVIE) {
            this.movieArchive.get(itemId).getSecond().getSecond().add(new Pair(userId, this.getToDay()));
            System.out.println("User " + userId + " adds to movie list " + itemId + " in date " + this.getToDay());
        } else {
            throw new RuntimeException("Error type.");
        }
    }

    @Override
    public void removeUser(final Type t, final Integer itemId, final Integer userId) throws Exception {
        if (t == Type.BOOK) {
            if (this.bookArchive.containsKey(itemId)) {
                int i = this.bookArchive.get(itemId).getSecond().getSecond().indexOf(userId);
                if (i > 0) {
                    this.bookArchive.get(itemId).getSecond().getSecond().remove(i);
                    System.out.println(
                            "User " + userId + " removes from book list " + itemId + " in date " + this.getToDay());
                } else {
                    throw new Exception("User: " + userId + "is not in the " + itemId + " list");
                }
            } else {
                throw new Exception("Book: " + itemId + "Not contained into the archive.");
            }
        }
        if (t == Type.MOVIE) {
            if (this.movieArchive.containsKey(itemId)) {
                int i = this.movieArchive.get(itemId).getSecond().getSecond().indexOf(userId);
                if (i > 0) {
                    this.movieArchive.get(itemId).getSecond().getSecond().remove(i);
                    System.out.println(
                            "User " + userId + " removes from movie list " + itemId + " in date " + this.getToDay());
                } else {
                    throw new Exception("User: " + userId + "is not in the " + itemId + " list");
                }
            } else {
                throw new Exception("Movie: " + itemId + "Not contained into the archive.");
            }
        }
        if ((t != Type.BOOK) && (t != Type.MOVIE)) {
            throw new RuntimeException("Error Type.");
        }
    }

    @Override
    public boolean checkAvailability(final Type t, final Integer itemId) throws Exception {
        if (t == Type.BOOK) {
            if (this.bookArchive.containsKey(itemId)) {
                int i = this.bookArchive.get(itemId).getSecond().getFirst()
                        - this.bookArchive.get(itemId).getSecond().getSecond().size();
                if (i == 0) {
                    System.out.println("Book: " + itemId + "not available");
                    return false;
                }
                if (i > 0) {
                    System.out.println("Book: " + itemId + "available");
                    return true;
                }
                if (i < 0) {
                    throw new RuntimeException("Amount < 0 in the bookArchive, BookId: " + itemId);
                }
            } else {
                throw new Exception("Book: " + itemId + "Not contained into the archive.");
            }
        }
        if (t == Type.MOVIE) {
            if (this.movieArchive.containsKey(itemId)) {
                int i = this.movieArchive.get(itemId).getSecond().getFirst()
                        - this.movieArchive.get(itemId).getSecond().getSecond().size();
                if (i == 0) {
                    System.out.println("Movie: " + itemId + "not available");
                    return false;
                }
                if (i > 0) {
                    System.out.println("Movie: " + itemId + "available");
                    return true;
                }
                if (i < 0) {
                    throw new RuntimeException("Amount < 0 in the movieArchive, MovieId: " + itemId);
                }
            } else {
                throw new Exception("Movie: " + itemId + "Not contained into the archive.");
            }
        }
        if ((t != Type.BOOK) && (t != Type.MOVIE)) {
            throw new RuntimeException("Error type.");
        }
        return false;
    }

    @Override
    public List<Integer> getUserList(final Type t, final Integer itemId) throws Exception {
        LinkedList<Integer> ls = new LinkedList<>();
        if (t == Type.BOOK) {
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
        return null;
    }
}
