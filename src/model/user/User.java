package model.user;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

import model.Pair;
import model.item.BookGenre;
import model.item.MovieGenre;

/**
 * User is the Mediateca client. This class saves in addition to general
 * information also preferences info, wishlist and recommended list of item.
 *
 * @author Edoardo
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2261594876176760469L;
    private final String name;
    private final String surname;
    private final GregorianCalendar birthdate;
    private final String username;
    private final String password;
    private final Integer idUser;
    private final String email;
    private final String telephoneNumber;
    private Map<Integer, Pair<Boolean, Optional<Integer>>> loanArchive = new HashMap<>();
    private List<Integer> wishList = new LinkedList<>();
    private List<Integer> recommendedList = new LinkedList<>();
    private boolean pro = false;
    private List<BookGenre> bookPreferences = new LinkedList<>();
    private List<MovieGenre> moviePreferences = new LinkedList<>();

    /**
     * User constructor.
     *
     * @param initName
     *            User's name.
     * @param initSurname
     *            User's surname.
     * @param initBirthdate
     *            User's day of birth.
     * @param initUsername
     *            User's username.
     * @param initPassword
     *            User's password.
     * @param initEmail
     *            User's email.
     * @param initTelephoneNumber
     *            User's telephone Number.
     * @param initBookPref
     *            User's preferences.
     * @param initMoviePref
     *            User's preferences.
     */
    public User(final String initName, final String initSurname, final GregorianCalendar initBirthdate,
            final String initUsername, final String initPassword, final String initEmail,
            final String initTelephoneNumber, final List<BookGenre> initBookPref,
            final List<MovieGenre> initMoviePref) {
        this.name = initName.toUpperCase();
        this.surname = initSurname.toUpperCase();
        this.birthdate = initBirthdate;
        this.username = initUsername;
        this.password = initPassword;
        this.idUser = this.hashCode();
        this.email = initEmail.toLowerCase();
        this.telephoneNumber = initTelephoneNumber;
        this.bookPreferences = initBookPref;
        this.moviePreferences = initMoviePref;
    }

    /**
     * @return the full user loanArchive.
     */
    public Map<Integer, Pair<Boolean, Optional<Integer>>> getLoanArchive() {
        return this.loanArchive;
    }

    /**
     * @return the whishList.
     */
    public List<Integer> getWhishList() {
        return this.wishList;
    }

    /**
     * @param initWhishList
     *            the whishList to set.
     */
    public void setWhishList(final List<Integer> initWhishList) {
        this.wishList = initWhishList;
    }

    /**
     * @return true if user is pro, else false.
     */
    public boolean isPro() {
        return this.pro;
    }

    /**
     * @param initPro
     *            the pro to set.
     */
    public void setPro(final boolean initPro) {
        this.pro = initPro;
    }

    /**
     * @return the bookPreferences
     */
    public List<BookGenre> getBookPreferences() {
        return this.bookPreferences;
    }

    /**
     * @param initBookPreferences
     *            the bookPreferences to set
     */
    public void setBookPreferences(final List<BookGenre> initBookPreferences) {
        this.bookPreferences = initBookPreferences;
    }

    /**
     * @return the moviePreferences
     */
    public List<MovieGenre> getMoviePreferences() {
        return this.moviePreferences;
    }

    /**
     * @param initMoviePreferences
     *            the moviePreferences to set
     */
    public void setMoviePreferences(final List<MovieGenre> initMoviePreferences) {
        this.moviePreferences = initMoviePreferences;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * @return the date of birth
     */
    public GregorianCalendar getBirthdate() {
        return this.birthdate;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return this.idUser;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    /**
     * @return the recommendedList
     */
    public List<Integer> getRecommendedList() {
        return this.recommendedList;
    }

    /**
     * @param initRecommendedList
     *            the recommendedList to set
     */
    public void setRecommendedList(final List<Integer> initRecommendedList) {
        this.recommendedList = initRecommendedList;
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(this.name, this.surname, this.birthdate, this.username);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        final User temp = (User) obj;
        return Objects.equal(this.name, temp.name) && Objects.equal(this.surname, temp.surname)
                && Objects.equal(this.birthdate, temp.birthdate) && Objects.equal(this.username, temp.username);
    }
}
