package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ArchiveImpl implements Serializable, Archive {

    private static final long serialVersionUID = 3943672353334594237L;
    private Map<Integer, Pair<Book, Pair<Integer, List<UserImpl>>>> bookArchive = new HashMap<>();
    private Map<Integer, Pair<Movie, Pair<Integer, List<UserImpl>>>> movieArchive = new HashMap<>();

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
    public String toString() {
        return "ArchiveImpl [BookArchive=" + this.bookArchive + ", MovieArchive=" + this.movieArchive + "]";
    }
}
