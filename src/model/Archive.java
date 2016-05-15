package model;

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
     * This function adds an ItemImpl to the respective archive.
     *
     * @param i
     *            Book or Movie in order to add to the respective archive.
     * @param initNumCopy
     *            Book or Movie number copies
     *
     * @param <X>
     *            Accept every X that S <: ItemImpl
     */
    <X extends ItemImpl> void addItem(X i, Integer initNumCopy);

    /**
     * This method is used to change item's setting.
     *
     * @param t
     *            Item's Type.
     * @param id
     *            Item's identifier.
     * @param initAmount
     *            amount of copy to add to the archive
     */
    void changeAmount(Type t, Integer id, Integer initAmount);

    /**
     * This method return an Item Type t with identifier id.
     *
     * @param t
     *            Item's type.
     * @param id
     *            Item's identifier.
     * @return Book or Movie (it depends by Type) with id code.
     */
    Item getItem(final Type t, final Integer id);

    /**
     * This method removes a Book or Movie from the respective archive.
     *
     * @param t
     *            Item's type
     * @param id
     *            Item's identifier.
     */
    void removeItem(final Type t, final Integer id);

    /**
     * This method apply dayBetweenDates to the required item of Type t with
     * identifier id.
     *
     * @param t
     *            Item's type
     *
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's Identifier.
     * @return the number of days elapsed
     *
     */
    double calculateDifferenceDays(final Type t, final Integer itemId, final Integer userId);

    /**
     * This method adds the user (userId) to the list of item's loans.
     *
     * @param t
     *            item's type.
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's identifier.
     */
    void addUser(final Type t, final Integer itemId, final Integer userId);

    /**
     * This method removes the user (userId) to the list of item's loans.
     *
     * @param t
     *            item's type.
     * @param itemId
     *            Item's identifier.
     * @param userId
     *            User's identifier.
     */
    void removeUser(final Type t, final Integer itemId, final Integer userId);

    /**
     * This method check the availability of the Item in the respective archive.
     * 
     * @param t
     *            item's type.
     * @param itemId
     *            item's identifier.
     * @return true if item is available else return false.
     */
    boolean checkAvailability(final Type t, final Integer itemId);

}
