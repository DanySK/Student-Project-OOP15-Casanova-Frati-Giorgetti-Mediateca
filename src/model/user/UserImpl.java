package model.user;

import java.io.Serializable;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

import model.Pair;
import model.item.ItemGenre;

/**
 * User is the Mediateca client. This class saves in addition to general
 * information also preferences info, wishlist and recommended list of item.
 *
 * @author Edoardo
 *
 */
public class UserImpl implements Serializable, User {

  private static final long serialVersionUID = 2261594876176760469L;
  private String name;
  private String surname;
  private GregorianCalendar birthdate;
  private String username;
  private String password;
  private Integer idUser;
  private String email;
  private String telephoneNumber;
  // Map<itemId, Pair<Restituito, reviewId>>
  private Map<Integer, Pair<Boolean, Optional<Integer>>> loanArchive = new HashMap<>();
  private List<Integer> wishList = new LinkedList<>();
  private List<Integer> recommendedList = new LinkedList<>();
  private List<ItemGenre> bookPreferences = new LinkedList<>();
  private List<ItemGenre> moviePreferences = new LinkedList<>();

  /**
   * User constructor.
   *
   * @param initName
   *          User's name.
   * @param initSurname
   *          User's surname.
   * @param initBirthdate
   *          User's day of birth.
   * @param initUsername
   *          User's username.
   * @param initPassword
   *          User's password.
   * @param initEmail
   *          User's email.
   * @param initTelephoneNumber
   *          User's telephone Number.
   * @param initBookPref
   *          User's preferences.
   * @param initMoviePref
   *          User's preferences.
   */
  public UserImpl(final String initName, final String initSurname,
              final GregorianCalendar initBirthdate, final String initUsername,
              final String initPassword, final String initEmail, final String initTelephoneNumber,
              final List<ItemGenre> initBookPref, final List<ItemGenre> initMoviePref) {
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

  @Override
  public Map<Integer, Pair<Boolean, Optional<Integer>>> getLoanArchive() {
    return Collections.unmodifiableMap(this.loanArchive);
  }

  @Override
  public List<Integer> getWhishList() {
    return Collections.unmodifiableList(this.wishList);
  }

  @Override
  public void addToWhishList(final Integer initWhishList) {
    this.wishList.add(initWhishList);
  }

  @Override
  public List<ItemGenre> getBookPreferences() {
    return Collections.unmodifiableList(this.bookPreferences);
  }

  @Override
  public void setBookPreferences(final List<ItemGenre> initBookPreferences) {
    this.bookPreferences = initBookPreferences;
  }

  @Override
  public List<ItemGenre> getMoviePreferences() {
    return Collections.unmodifiableList(this.moviePreferences);
  }

  @Override
  public void setMoviePreferences(final List<ItemGenre> initMoviePreferences) {
    this.moviePreferences = initMoviePreferences;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getSurname() {
    return this.surname;
  }

  @Override
  public GregorianCalendar getBirthdate() {
    return this.birthdate;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public Integer getIdUser() {
    return this.idUser;
  }

  @Override
  public String getEmail() {
    return this.email;
  }

  @Override
  public String getTelephoneNumber() {
    return this.telephoneNumber;
  }

  @Override
  public List<Integer> getRecommendedList() {
    return Collections.unmodifiableList(this.recommendedList);
  }

  @Override
  public void setRecommendedList(final List<Integer> initRecommendedList) {
    this.recommendedList = initRecommendedList;
  }

  /**
   * @param initName
   *          the name to set.
   */
  public void setName(final String initName) {
    this.name = initName.toUpperCase();
  }

  /**
   * @param initSurname
   *          the surname to set
   */
  public void setSurname(final String initSurname) {
    this.surname = initSurname.toUpperCase();
  }

  /**
   * @param initBirthdate
   *          the birthdate to set
   */
  public void setBirthdate(final GregorianCalendar initBirthdate) {
    this.birthdate = initBirthdate;
  }

  /**
   * @param initUsername
   *          the username to set
   */
  public void setUsername(final String initUsername) {
    this.username = initUsername;
  }

  /**
   * @param initPassword
   *          the password to set
   */
  public void setPassword(final String initPassword) {
    this.password = initPassword;
  }

  /**
   * @param initEmail
   *          the email to set
   */
  public void setEmail(final String initEmail) {
    this.email = initEmail.toLowerCase();
  }

  /**
   * @param initTelephoneNumber
   *          the telephoneNumber to set
   */
  public void setTelephoneNumber(final String initTelephoneNumber) {
    this.telephoneNumber = initTelephoneNumber;
  }

  @Override
  public int hashCode() {
    return com.google.common.base.Objects.hashCode(this.name, this.surname, this.birthdate,
                this.username);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof UserImpl)) {
      return false;
    }
    final UserImpl temp = (UserImpl) obj;
    return Objects.equal(this.name, temp.name) && Objects.equal(this.surname, temp.surname)
                && Objects.equal(this.birthdate, temp.birthdate)
                && Objects.equal(this.username, temp.username);
  }

  @Override
  public void addItem(final Integer itemId) {
    this.loanArchive.put(itemId, new Pair<>(false, null));
  }

  @Override
  public void removeItem(final Integer itemId) throws Exception {
    if (this.loanArchive.containsKey(itemId)) {
      this.loanArchive.put(itemId, new Pair<>(true, null));
    } else {
      throw new Exception("ItemId" + itemId + " is not in the archive.");
    }
  }

  @Override
  public void setItemReview(final Integer itemId, final Integer reviewId) {
    this.loanArchive.put(itemId, new Pair<Boolean, Optional<Integer>>(
                this.loanArchive.get(itemId).getFirst(), Optional.of(reviewId)));
  }

  @Override
  public boolean itWasReturned(final Integer itemId) {
    return this.loanArchive.get(itemId).getFirst();
  }

  @Override
  public Optional<Integer> getListReview(final Integer itemId) {
    return this.loanArchive.get(itemId).getSecond();
  }
}
