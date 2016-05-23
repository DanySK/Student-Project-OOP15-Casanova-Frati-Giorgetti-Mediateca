package model.item;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.base.Objects;

/**
 * Item is the common part of book and movie. It implements its interface Item
 * and Serializable in order to save data.
 *
 * @author Edoardo
 *
 */
public class ItemImpl implements Item, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7331548557628545541L;
    private final int iD;
    private final String title;
    private final int releaseYear;
    private final String publisher;
    private final String author;
    private final Language currentLanguage;
    private List<ReviewImpl> setReview = new LinkedList<ReviewImpl>();
    private Set<Integer> like;
    private float averageVote;

    /**
     * Item's constructors whit starter initialization. In the field ID there is
     * the global item's IDenti
     *
     * @param initTitle
     *            of the general item
     * @param initReleaseYear
     *            of the general item
     * @param initPublisher
     *            of the general item
     * @param initAuthor
     *            author in the case of book, director in the case of movie
     * @param initCurrentLanguage
     *            of the general item contained in the archive
     */
    public ItemImpl(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage) {
        this.title = initTitle.toUpperCase();
        this.releaseYear = initReleaseYear;
        this.publisher = initPublisher.toUpperCase();
        this.author = initAuthor.toUpperCase();
        this.currentLanguage = initCurrentLanguage;
        this.like = new HashSet<Integer>();
        this.averageVote = 0;
        this.iD = this.hashCode();
    }

    @Override
    public String toString() {
        return "[iD=" + this.iD + ", title=" + this.title + ", releaseYear=" + this.releaseYear + ", publisher="
                + this.publisher + ", author=" + this.author + ", currentLanguage=" + this.currentLanguage
                + ", setReview=" + this.setReview.toString() + ", like=" + this.like + ", averageVote="
                + this.averageVote;
    }

    @Override
    public String getPublisher() {
        return this.publisher;
    }

    @Override
    public Language getCurrentLanguage() {
        return this.currentLanguage;
    }

    @Override
    public int getiD() {
        return this.iD;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getReleaseYear() {
        return this.releaseYear;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public List<ReviewImpl> getSetReview() {
        return this.setReview;
    }

    @Override
    public int getLike() {
        return this.like.size();
    }

    public Set<Integer> getLikeUser() {
        return this.like;
    }

    @Override
    public float getAverageVote() {
        return this.averageVote;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.author, this.releaseYear, this.title, this.publisher, this.currentLanguage);
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof ItemImpl)) {
            return false;
        }
        final ItemImpl item = (ItemImpl) object;
        return Objects.equal(this.author, item.author) && Objects.equal(this.releaseYear, item.releaseYear)
                && Objects.equal(this.title, item.title) && Objects.equal(this.publisher, item.publisher)
                && Objects.equal(this.currentLanguage, item.currentLanguage);
    }

    @Override
    public void addReview(final ReviewImpl rev) {
        this.getSetReview().add(rev);
        this.setAverageVote();
    }

    @Override
    public void addLike(final Integer userId) {
        if (!this.like.contains(userId)) {
            this.like.add(userId);
        }
    }

    @Override
    public void setAverageVote() {
        int div = this.setReview.size();
        int total = 0;
        for (Review r : this.setReview) {
            total = total + r.getVote();
        }
        this.averageVote = (float) total / div;
    }
}
