package model;

import java.awt.Image;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

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
    private Optional<Image> cover = Optional.absent();
    private List<Review> setReview = new LinkedList<Review>();
    private int like;

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
     * @param initCover
     *            of the general item
     */
    public ItemImpl(final String initTitle, final int initReleaseYear, final String initPublisher,
            final String initAuthor, final Language initCurrentLanguage, final Image initCover) {
        this.title = initTitle;
        this.releaseYear = initReleaseYear;
        this.publisher = initPublisher;
        this.author = initAuthor;
        this.currentLanguage = initCurrentLanguage;
        this.cover = initCover == null ? Optional.absent() : Optional.of(initCover);
        this.like = 0;
        this.iD = this.hashCode();
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
    public Optional<Image> getCover() {
        return this.cover;
    }

    @Override
    public List<Review> getSetReview() {
        return this.setReview;
    }

    @Override
    public int getLike() {
        return this.like;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.author, this.releaseYear, this.title, this.publisher);
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof ItemImpl)) {
            return false;
        }
        final ItemImpl item = (ItemImpl) object;
        return Objects.equal(this.author, item.author) && Objects.equal(this.releaseYear, item.releaseYear)
                && Objects.equal(this.title, item.title);
    }

    @Override
    public void addReview(final Review rev) {
        this.getSetReview().add(rev);
    }

    @Override
    public void addLike() {
        this.like++;
    }
}
