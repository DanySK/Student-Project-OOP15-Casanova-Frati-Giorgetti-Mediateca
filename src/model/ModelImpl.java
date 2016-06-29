package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.item.ArchiveImpl;
import model.item.ArchiveImpl.TypeItem;
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

    @Override
    public void registerUser(final String initName, final String initSurname, final GregorianCalendar initBirthdate,
            final String initUsername, final String initPassword, final String initEmail,
            final String initTelephoneNumber, final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref)
                    throws Exception {
        this.archiveUser.addUser(new User(initName, initSurname, initBirthdate, initUsername, initPassword, initEmail,
                initTelephoneNumber, initBookPref, initMoviePref));

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
    public void registerBook(final String initTitle, final int initReleaseYear, final String initAuthor,
            final Language initCurrentLanguage, final String initISBN, final ItemGenre initGenre,
            final String initPublisher, final Integer initNumRelease, final Integer initNumCopy) throws Exception {
        this.archiveItem.addItem(ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor, initCurrentLanguage,
                initISBN, initGenre, initPublisher, initNumRelease), initNumCopy);

    }

    @Override
    public void registerMovie(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage, final ItemGenre initGenre,
            final Integer initDuration, final Boolean initColor, final Integer initNumCopy) throws Exception {
        this.archiveItem.addItem(ItemFactory.getNewMovie(initTitle, initReleaseYear, initPublisher, initAuthor,
                initCurrentLanguage, initGenre, initDuration, initColor), initNumCopy);
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
            throw new Exception("ItemId: " + itemId + " or userId" + userId + "are not contained into the archive");
        }

    }

    @Override
    public void returnItem(final int itemId, final int userId) throws Exception {
        if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
            this.archiveItem.removeUser(itemId, userId);
            this.archiveUser.getUser(userId).removeItem(itemId);
        } else {
            throw new Exception("ItemId: " + itemId + " or userId" + userId + "are not contained into the archive");
        }

    }

    @Override
    public void addLike(final int itemId, final int userId) throws Exception {
        if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
            this.archiveItem.getItem(itemId).addLike(userId);
        } else {
            throw new Exception("ItemId: " + itemId + " or userId" + userId + "are not contained into the archive");
        }
    }

    @Override
    public void addReview(final Integer itemId, final Integer userId, final Integer vote, final String note)
            throws Exception {
        ReviewImpl rev = new ReviewImpl(vote, note);
        if (this.archiveUser.contains(userId) && this.archiveItem.contains(itemId)) {
            this.archiveUser.getUser(userId).setItemReview(itemId, (int) rev.getId());
            this.archiveItem.getItem(itemId).addReview(rev);
        } else {
            throw new Exception("ItemId: " + itemId + " or userId" + userId + "are not contained into the archive\n");
        }
    }

    @Override
    public Set<Integer> getAllItemId(final TypeItem t) {
        return this.archiveItem.getItemId(t);
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

    public Set<Integer> filterBookGenre(final ItemGenre b) {
        Set<Integer> r = new HashSet<>();
        Set<Integer> all = new HashSet<>(this.getAllItemId(TypeItem.BOOK));
        for (Integer i : all) {
            try {
                if (this.archiveItem.getItem(i).getGenre().equals(b)) {
                    r.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r;
    }
}
