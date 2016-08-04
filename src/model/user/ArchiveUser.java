package model.user;

import java.util.Map;
import java.util.Set;

/**
 * This interface contains methods to communicate with user archive.
 *
 * @author Edoardo
 *
 */
public interface ArchiveUser {

  /**
   * This method return the map of user into the archive.
   *
   * @return the userArchive
   */
  Map<Integer, User> getUserArchive();

  /*
   * This method set the map of user into the archive.
   *
   * @param initUserArchive the userArchive to set.
   * 
   * void setUserArchive(final Map<Integer, User> initUserArchive);
   */

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
  void setArchiveImpl(final Map<Integer, User> initUserArchive) throws Exception;

  /**
   * This method adds a User into the archive.
   *
   * @param initUser
   *          the object user in order to add to the archive.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  void addUser(final User initUser) throws Exception;

  /**
   * This method remove/delete user from the archive.
   *
   * @param userId
   *          user's identifier.
   * @throws Exception
   *           if user is not contained into the archive.
   */
  void removeUser(final Integer userId) throws Exception;

  /**
   * This method return the required user with identifier = userId.
   *
   * @param userId
   *          user's identifier.
   * @return User with userId
   * @throws Exception
   *           if user is not contained into the archive.
   */
  User getUser(final Integer userId) throws Exception;

  /**
   * @param userId
   *          to find into the archive.
   * @return true if userId is into the archive, else false.
   */
  boolean contains(final Integer userId);

  /**
   * This method return a Set of all UserId in the archive.
   *
   * @return UserId set.
   */
  Set<Integer> getUserId();
}
