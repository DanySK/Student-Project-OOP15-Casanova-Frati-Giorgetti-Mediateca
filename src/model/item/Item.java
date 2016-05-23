package model.item;

import java.util.List;

/**
 * Item is the center interface of Book and Movie.
 *
 * @author Edoardo
 *
 */
public interface Item {

    /**
     *
     * @return Item's publisher.
     */
    String getPublisher();

    /**
     *
     * @return Item's current language
     */
    Language getCurrentLanguage();

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
     *
     * @return Item's iD
     */
    int getiD();

    /**
     *
     * @return Item's title
     */
    String getTitle();

    /**
     *
     * @return Item's releaseYear
     */
    int getReleaseYear();

    /**
     *
     * @return Item's author
     */
    String getAuthor();

    /**
     *
     * @return Item's List of Review, if it is present.
     */
    List<ReviewImpl> getSetReview();

    /**
     *
     * @return Item's number of like received.
     */
    int getLike();

    /**
     * Add a review to the Item.
     *
     * @param rev
     *            in order to add this review to the item's set
     */
    void addReview(ReviewImpl rev);

    /**
     * Add like to the Item.
     */
    void addLike();

    /**
     * Calculate the average of the review's vote.
     */
    void setAverageVote();

    /**
     *
     * @return Item's average vote.
     */
    float getAverageVote();

}