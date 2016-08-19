package model.item;

/**
 * Item is the center interface of Book and Movie.
 *
 * @author Edoardo
 *
 */
public interface Item {

  /**
   * hashCode function uses the Objects.hashCode( field1, field2, .. ) taken
   * from Google Guava.
   */
  @Override
  int hashCode();

  /**
   * equals function uses Object.equal(obj1, obj2) taken from Google Guava.
   *
   * @param obj
   * @return
   */
  @Override
  boolean equals(final Object obj);

  /**
   * Add a review to the Item.
   *
   * @param rev
   *          in order to add this review to the item's set
   */
  void addReview(ReviewImpl rev);

  /**
   * Add userId from the set which contains the user that likes it.
   *
   * @param userId
   *          user's identifier that likes this item.
   */
  void addLike(final Integer userId);

  /**
   * Remove userId from the set which contains the user that likes it.
   *
   * @param userId
   *          user's identifier.
   */
  void removeLike(final Integer userId);

}
