package model;

import java.util.Set;

/**
 * This interface contains methods to communicate with the archives.
 *
 * @author Edoardo
 *
 */
public interface Archive {

    /**
     * Internal enum to identify Item Type.
     *
     * @author Edoardo
     *
     */
    enum Type {
        BOOK, MOVIE
    }

    /**
     * This function adds an ItemImpl to the archive.
     *
     * @param i
     *            Book or Movie to add to the archive.
     * @param initNumCopy
     *            Book or Movie number copies
     * @throws Exception
     *             in the case which initNumCopy < 0 or item not in the archive.
     *
     */
    void addItem(final ItemImpl i, final Integer initNumCopy) throws Exception;

    /**
     * This method is used to change item's setting.
     *
     * @param itemId
     *            Item's identifier.
     * @param initAmount
     *            amount of copy to add to the archive
     * @throws Exception
     *             in the case which the item is not in the archive.
     */
    void changeAmount(final Integer itemId, final Integer initAmount) throws Exception;

    /**
     * This method return an Item identified by id.
     *
     * @param itemId
     *            Item's identifier.
     *
     * @return Item with id code.
     *
     * @throws Exception
     *             in the case which the item is not in the archive.
     */
    Item getItem(final Integer itemId) throws Exception;

    /**
     * This method removes a Item from the respective archive.
     *
     * @param id
     *            Item's identifier.
     * @throws Exception
     *             in the case which the item is not in the archive.
     */
    void removeItem(final Integer id) throws Exception;

    /**
     * This method apply dayBetweenDates to the required item with identifier
     * id.
     *
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's Identifier.
     * @return the number of days elapsed
     * @throws Exception
     *             in the case which the item is not in the archive or the user
     *             is not in the item's list.
     *
     */
    double calculateDifferenceDays(final Integer itemId, final Integer userId) throws Exception;

    /**
     * This method adds the user (userId) to the list of item's loans.
     *
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's identifier.
     * @throws Exception
     *             in the case which the item is not in the archive.
     */
    void addUser(final Integer itemId, final Integer userId) throws Exception;

    /**
     * This method removes the user (userId) to the list of item's loans.
     *
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's identifier.
     * @throws Exception
     *             in the case which the item is not in the archive or the user
     *             is not in the item's list.
     */
    void removeUser(final Integer itemId, final Integer userId) throws Exception;

    /**
     * This method check the availability of the Item in the respective archive.
     *
     * @param itemId
     *            item's identifier.
     * @return true if item is available else return false.
     * @throws Exception
     *             in the case which the item required is not present in the
     *             archive.
     */
    boolean checkAvailability(final Integer itemId) throws Exception;

    /**
     * This method return a list of UserID that have taken a required Item from
     * the archives.
     *
     * @param itemId
     *            Item's identifier.
     * @return the Set of users identifier that have taken a required Item from
     *         the archives.
     * @throws Exception
     *             in the case which the item required is not present in the
     *             archive.
     */
    Set<Integer> getUserList(final Integer itemId) throws Exception;

}
