package model;

import java.util.GregorianCalendar;

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
     * This method calculates the difference of days elapsed from the day when
     * Item was taken from archive by userId. It uses the current date to
     * calculate the difference.
     *
     * @param fromDate
     *            item's taken date.
     * @return the number of days elapsed.
     */
    double dayBetweenDates(final GregorianCalendar fromDate);

}
