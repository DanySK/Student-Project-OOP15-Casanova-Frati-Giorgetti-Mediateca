package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArchiveImpl implements Serializable, Archive {

    private static final long serialVersionUID = 3943672353334594237L;
    public static final double SSINDD = 86400000.0;
    private Map<Integer, Pair<Book, Pair<Integer, List<Pair<Integer, GregorianCalendar>>>>> bookArchive = new HashMap<>();
    private Map<Integer, Pair<Movie, Pair<Integer, List<Pair<Integer, GregorianCalendar>>>>> movieArchive = new HashMap<>();

    /**
     * Empty constructor.
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

    @Override
    public double dayBetweenDates(final GregorianCalendar fromDate) {

        // Current date
        Calendar c = Calendar.getInstance();
        GregorianCalendar toDate = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

        // Conversion in ms
        long msFromDate = fromDate.getTimeInMillis();
        long msToDate = toDate.getTimeInMillis();

        long msBetween = msToDate - msFromDate;

        // Conversion in days with math rounding
        double ddBetween = Math.round(msBetween / ArchiveImpl.SSINDD);

        return ddBetween;
    }

}
