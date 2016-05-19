package model.user;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ArchiveUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -891777149481744993L;
    private Map<Integer, User> userArchive = new HashMap<>();

    /**
     * Empty constructor.
     */
    public ArchiveUser() {
    }

    public void addUser(final User initUser) throws Exception {
        if (!this.userArchive.containsKey(initUser.getIdUser())) {
            this.userArchive.put(initUser.getIdUser(), initUser);
        } else {
            throw new Exception(
                    "User: " + initUser.getIdUser() + "contained into the archive.Can not add one more time.");
        }
    }
}
