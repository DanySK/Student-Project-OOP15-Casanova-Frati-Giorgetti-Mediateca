package model.user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
     *            the object user in order to add to the archive.
     * @throws Exception
     *             if user is not contained into the archive.
     */
    public void addUser(final User initUser) throws Exception {
        if (!this.userArchive.containsKey(initUser.getIdUser())) {
            this.userArchive.put(initUser.getIdUser(), initUser);
        } else {
            throw new Exception(
                    "User: " + initUser.getIdUser() + "contained into the archive.Can not add one more time.");
        }
    }

    /**
     * This method remove/delete user from the archive.
     *
     * @param userId
     *            user's identifier.
     * @throws Exception
     *             if user is not contained into the archive.
     */
    public void removeUser(final Integer userId) throws Exception {
        if (this.userArchive.containsKey(userId)) {
            this.userArchive.remove(userId);
        } else {
            throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
        }
    }

    /**
     * This method return the required user with identifier = userId.
     *
     * @param userId
     *            user's identifier.
     * @return User with userId
     * @throws Exception
     *             if user is not contained into the archive.
     */
    public User getUser(final Integer userId) throws Exception {
        if (this.userArchive.containsKey(userId)) {
            return this.userArchive.get(userId);
        } else {
            throw new Exception("User: " + userId + " not contained into the archive.Can not remove it");
        }
    }
}
