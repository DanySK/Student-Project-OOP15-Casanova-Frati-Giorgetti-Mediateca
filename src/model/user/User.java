package model.user;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

import model.Pair;

/**
 * User is the Mediateca client. This class saves in addition to general
 * information also preferences info, wishlist and recommended list of item.
 *
 * @author Edoardo
 *
 */
public interface User {

  /**
   * @return the full user loanArchive.
   */
  Map<Integer, Pair<Boolean, Optional<Integer>>> getLoanArchive();

  /**
   * This method adds to the wishlist the required itemId.
   *
   * @param initWishList
   *          the whishList to set.
   */
  void addToWishList(final Integer initWishList);

  /**
   * This method removes from the wishlist the requiredItem.
   *
   * @param itemId
   *          item's identifier.
   */
  void removeFromWishList(final Integer itemId);

  /**
   * This method adds an itemId to the user's map.
   *
   * @param itemId
   *          item's identifier.
   */
  void addItem(final Integer itemId);

  /**
   * This method change item's status on the user's map.
   *
   * @param itemId
   *          item's identifier.
   * @throws Exception
   *           in the case which itemId is not in the archive.
   */
  void removeItem(final Integer itemId) throws Exception;

  /**
   * This method adds a reviewId to the respective item on the user's map, it
   * doesn't change return/not status.
   *
   * @param itemId
   *          item's identifier.
   * @param reviewId
   *          review's identifier.
   */
  void setItemReview(final Integer itemId, final Integer reviewId);

  /**
   * This method @return true(false) if @param itemId was(wasn't) returned by
   * the user.
   *
   * @return true if itemId was return else false.
   */
  boolean itWasReturned(final Integer itemId);

  /**
   * This method return the reviewId done by the user. It is optional.
   *
   * @param itemId
   *          item's identifier.
   * @return the reviewId if it is present.
   */
  Optional<Integer> getListReview(final Integer itemId);

  /**
   * This method return a list of item now borrowed. ( out of mediateca )
   * 
   * @return list of item id which are out of mediateca
   */
  Set<Integer> getNowOnLoan();
}
