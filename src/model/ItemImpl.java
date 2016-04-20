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
    private final String author;
    private Optional<Image> cover = Optional.absent();
    private List<Review> setReview = new LinkedList<Review>();
    private int like;

    /**
     * Item's constructors whit starter initialization. In the field ID there is
     * the global item's IDentifier
     *
     * @param title
     * @param releaseYear
     * @param author
     * @param cover
     */
    public ItemImpl(final String title, final int releaseYear, final String author, final Image cover) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.author = author;
        this.cover = Optional.fromNullable(cover);
        this.like = 0;
        this.iD = this.hashCode();
    }

    /**
     * Builder constructor pattern.
     *
     * @author Edoardo
     *
     */
    public static class BuilderItem {

        private Integer iD;
        public String title;
        public Integer releaseYear;
        public String author;
        public Image cover;
        private List<Review> setReview = new LinkedList<Review>();
        private Integer like;

        /**
         * Public constructor.
         */
        public BuilderItem() {
        }

        /**
         * Build iD's field.
         *
         * @param i
         *            glob identifier
         * @return this.iD
         *
         */
        public BuilderItem iD(final int i) {
            this.iD = this.hashCode();
            return this;
        }

        /**
         * Build title's field.
         *
         * @param s
         *            item's title
         * @return title
         *
         */
        public BuilderItem title(final String s) {
            this.title = s;
            return this;
        }

        /**
         * Build releaseYear's field.
         *
         * @param i
         *            item's release year
         * @return realeseYear
         */
        public BuilderItem releaseYear(final int i) {
            this.releaseYear = i;
            return this;
        }

        /**
         * Build author's field.
         *
         * @param s
         *            item's author
         * @return author
         *
         */
        public BuilderItem author(final String s) {
            this.author = s;
            return this;
        }

        /**
         * Build cover's field.
         *
         * @param img
         *            item's cover
         * @return cover
         */
        public BuilderItem cover(final Image img) {
            this.cover = img;
            return this;
        }

        /**
         * Join insert field.
         *
         * @return new ItemImpl
         * @throws IllegalStateException
         *             if args are null
         */
        public ItemImpl build() throws IllegalStateException {
            if ((this.title == null) || (this.releaseYear == null) || (this.author == null)) {
                throw new IllegalStateException("Param null");
            }
            return new ItemImpl(this.title, this.releaseYear, this.author, this.cover);
        }
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
        return Objects.hashCode(this.author, this.releaseYear, this.title);
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
