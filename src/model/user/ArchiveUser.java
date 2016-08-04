package model.user;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class implements Serializable, it has only one field which contains the
 * map of user register to the 'Mediateca'.
 *
 * @author Edoardo
 *
 */
public final class ArchiveUser implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -891777149481744993L;
  private static ArchiveUser singleton = null;
  private Map<Integer, User> userArchive = new HashMap<>();

  private ArchiveUser() {
  }

  /**
   * This method return the map of user into the archive.
   *
   * @return the userArchive
   */
  public Map<Integer, User> getUserArchive() {
    return this.userArchive;
  }

  /**
   * This method set the map of user into the archive.
   *
   * @param initUserArchive
   *          the userArchive to set.
   */
  public void setUserArchive(final Map<Integer, User> initUserArchive) {
    this.userArchive = initUserArchive;
  }

  /**
   * This method must be called only at the start of the program, if it had
   * already file config saved. With this method you set the main field
   * userArchive.
   *
   * @param initUserArchive
   *          saved user's archive.
   * @throws Exception
   *           in the case which field it's already initialized.
   */
  public void setArchiveImpl(final Map<Integer, User> initUserArchive) throws Exception {
    if (ArchiveUser.singleton == null) {
      ArchiveUser.getArchiveImpl().setUserArchive(initUserArchive);
    } else {
      throw new Exception("Item archive already loaded");
    }
  }

  /**
   * Singleton constructor.
   *
   * @return the ArchiveUser.
   */
  public static ArchiveUser getArchiveImpl() {
    if (ArchiveUser.singleton == null) {
      ArchiveUser.singleton = new ArchiveUser();
    }
    return ArchiveUser.singleton;
  }

  /**
   * This method adds a User into the archive.
   *
   * @param initUser
   *          the object user in order to add to the archive.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  public void addUser(final User initUser) throws Exception {
    if (!ArchiveUser.singleton.contains(initUser.getIdUser())) {
      ArchiveUser.singleton.getUserArchive().put(initUser.getIdUser(), initUser);

    } else {
      throw new Exception("User: " + initUser.getIdUser()
                  + "contained into the archive.Can not add one more time.");
    }
  }

  /**
   * This method remove/delete user from the archive.
   *
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  public void removeUser(final Integer userId) throws Exception {
    if (ArchiveUser.singleton.contains(userId)) {
      ArchiveUser.singleton.getUserArchive().remove(userId);
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }
  }

  /**
   * This method return the required user with identifier = userId.
   *
   * @param userId
   *          user's identifier.
   * @return User with userId
   * @throws Exception
   *           if user is not contained into the archive.
   */
  public User getUser(final Integer userId) throws Exception {
    if (ArchiveUser.singleton.contains(userId)) {
      return ArchiveUser.singleton.getUserArchive().get(userId);
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }
  }

  /**
   * @param userId
   *          to find into the archive.
   * @return true if userId is into the archive, else false.
   */
  public boolean contains(final Integer userId) {
    return ArchiveUser.singleton.getUserArchive().containsKey(userId);
  }

  /**
   * This method return a Set of all UserId in the archive.
   *
   * @return UserId set.
   */
  public Set<Integer> getUserId() {
    return Collections.unmodifiableSet(ArchiveUser.singleton.getUserArchive().keySet());
  }
}
