package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import model.item.ArchiveImpl;
import model.item.BookGenre;
import model.item.ItemFactory;
import model.item.Language;
import model.item.MovieGenre;
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

    /**
     *
     */
    private static final long serialVersionUID = -8370710936091204583L;
    private ArchiveImpl archiveItem = ArchiveImpl.getArchiveImpl();
    private ArchiveUser archiveUser = ArchiveUser.getArchiveImpl();

    @Override
    public void registerUser(final String initName, final String initSurname, final GregorianCalendar initBirthdate,
            final String initUsername, final String initPassword, final String initEmail,
            final String initTelephoneNumber, final List<BookGenre> initBookPref, final List<MovieGenre> initMoviePref)
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
            final Language initCurrentLanguage, final String initISBN, final BookGenre initGenre,
            final String initPublisher, final Integer initNumRelease, final Integer initNumCopy) throws Exception {
        this.archiveItem.addItem(ItemFactory.getNewBook(initTitle, initReleaseYear, initAuthor, initCurrentLanguage,
                initISBN, initGenre, initPublisher, initNumRelease), initNumCopy);

    }

    @Override
    public void registerMovie(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage, final MovieGenre initGenre,
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
        } else {
            throw new Exception("ItemId: " + itemId + " or userId" + userId + "are not contained into the archive");
        }

    }

    @Override
    public void returnItem(final int itemId, final int userId) throws Exception {
        if (this.archiveItem.contains(itemId) && this.archiveUser.contains(userId)) {
            this.archiveItem.removeUser(itemId, userId);
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

}
