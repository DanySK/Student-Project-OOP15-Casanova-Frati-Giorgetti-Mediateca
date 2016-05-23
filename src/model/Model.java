package model;

import java.util.GregorianCalendar;
import java.util.List;

import model.item.BookGenre;
import model.item.Language;
import model.item.MovieGenre;

/**
 * Interface for a generic model. It defines the main methods of the class
 * model, it's effectively the 'Mediateca' itself with user and item archives.
 *
 * @author Edoardo
 *
 */
public interface Model {

    /**
     * This method registers a user into the user archive.
     *
     * @param initName
     *            user's name.
     * @param initSurname
     *            user's surname.
     * @param initBirthdate
     *            date user's birth.
     * @param initUsername
     *            user's user-name.
     * @param initPassword
     *            user's password.
     * @param initEmail
     *            user's email.
     * @param initTelephoneNumber
     *            user's telephone number.
     * @param initBookPref
     *            user's list of BookGenre preferences.
     * @param initMoviePref
     *            user's list of MovieGenre preferences.
     * @throws Exception
     *             in the case which Users already is into the archive.
     */
    void registerUser(final String initName, final String initSurname, final GregorianCalendar initBirthdate,
            final String initUsername, final String initPassword, final String initEmail,
            final String initTelephoneNumber, final List<BookGenre> initBookPref, final List<MovieGenre> initMoviePref)
                    throws Exception;

    /**
     * This method removes the user with userId from the archive.
     *
     * @param userId
     *            user's identifier.
     */
    void deleteUser(final int userId);

    /**
     * This method registers the book into the item archive.
     *
     * @param initTitle
     *            item's title.
     * @param initReleaseYear
     *            item's release year.
     * @param initAuthor
     *            item's author.
     * @param initCurrentLanguage
     *            item's language.
     * @param initISBN
     *            book's code.
     * @param initGenre
     *            item's genre.
     * @param initPublisher
     *            item's publisher.
     * @param initNumRelease
     *            item's num of release.
     * @param initNumCopy
     *            item's num of copy available.
     * @throws Exception
     *             in the case which initNumCopy <= 0.
     */
    void registerBook(final String initTitle, final int initReleaseYear, final String initAuthor,
            final Language initCurrentLanguage, final String initISBN, final BookGenre initGenre,
            final String initPublisher, final Integer initNumRelease, final Integer initNumCopy) throws Exception;

    /**
     * This method registers the book into the item archive.
     *
     * @param initTitle
     *            item's title.
     * @param initReleaseYear
     *            item's release year.
     * @param initPublisher
     *            item's publisher.
     * @param initAuthor
     *            item's author.
     * @param initCurrentLanguage
     *            item's language.
     * @param initGenre
     *            item's genre.
     * @param initDuration
     *            item's duration in min.
     * @param initColor
     *            item's color.
     * @param initNumCopy
     *            item's num of copy available.
     * @throws Exception
     *             in the case which initNumCOpy <= 0
     */
    void registerMovie(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage, final MovieGenre initGenre,
            final Integer initDuration, final Boolean initColor, final Integer initNumCopy) throws Exception;

    /**
     * This method removes an item from the archive.
     *
     * @param itemId
     *            item's identifier.
     * @throws Exception
     *             in the case which itemId is not into the archive.
     */
    void deleteItem(final int itemId) throws Exception;

    /**
     * This method is used to associate a book with a user that borrow it.
     *
     * @param itemId
     *            item's identifier.
     * @param userId
     *            user's identifier.
     */
    void borrowItem(final int itemId, final int userId);

    /**
     * This method is used to dissociate a book with a user that return it.
     *
     * @param itemId
     *            item's identifier.
     * @param userId
     *            user's identifier.
     */
    void returnItem(final int itemId, final int userId);

}
