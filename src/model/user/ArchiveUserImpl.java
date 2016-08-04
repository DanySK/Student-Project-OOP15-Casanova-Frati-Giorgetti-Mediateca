package model.user;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class implements Serializable and ArchiveUser, it has only one field
 * which contains the map of user register to the 'Mediateca'.
 *
 * @author Edoardo
 *
 */
public final class ArchiveUserImpl implements Serializable, ArchiveUser {

  /**
   *
   */
  private static final long serialVersionUID = -891777149481744993L;
  private static ArchiveUserImpl singleton = null;
  private Map<Integer, User> userArchive = new HashMap<>();

  private ArchiveUserImpl() {
  }

  @Override
  public Map<Integer, User> getUserArchive() {
    return this.userArchive;
  }

  private void setUserArchive(final Map<Integer, User> initUserArchive) {
    this.userArchive = initUserArchive;
  }

  @Override
  public void setArchiveImpl(final Map<Integer, User> initUserArchive) throws Exception {
    if (ArchiveUserImpl.singleton == null) {
      ArchiveUserImpl.getArchiveImpl().setUserArchive(initUserArchive);
    } else {
      throw new Exception("Item archive already loaded");
    }
  }

  /**
   * Singleton constructor.
   *
   * @return the ArchiveUser.
   */
  public static ArchiveUserImpl getArchiveImpl() {
    if (ArchiveUserImpl.singleton == null) {
      ArchiveUserImpl.singleton = new ArchiveUserImpl();
    }
    return ArchiveUserImpl.singleton;
  }

  @Override
  public void addUser(final User initUser) throws Exception {
    if (!ArchiveUserImpl.singleton.contains(initUser.getIdUser())) {
      ArchiveUserImpl.singleton.getUserArchive().put(initUser.getIdUser(), initUser);

    } else {
      throw new Exception("User: " + initUser.getIdUser()
                  + "contained into the archive.Can not add one more time.");
    }
  }

  @Override
  public void removeUser(final Integer userId) throws Exception {
    if (ArchiveUserImpl.singleton.contains(userId)) {
      ArchiveUserImpl.singleton.getUserArchive().remove(userId);
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }
  }

  @Override
  public User getUser(final Integer userId) throws Exception {
    if (ArchiveUserImpl.singleton.contains(userId)) {
      return ArchiveUserImpl.singleton.getUserArchive().get(userId);
    } else {
      throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
    }
  }

  @Override
  public boolean contains(final Integer userId) {
    return ArchiveUserImpl.singleton.getUserArchive().containsKey(userId);
  }

  @Override
  public Set<Integer> getUserId() {
    return Collections.unmodifiableSet(ArchiveUserImpl.singleton.getUserArchive().keySet());
  }
}
