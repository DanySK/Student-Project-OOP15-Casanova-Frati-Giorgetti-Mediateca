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
  private ItemGenre genre;
  private float averageVote;

  /**
   * Item's constructors whit starter initialization. In the field ID there is
   * the global item's IDenti
   *
   * @param initTitle
   *          of the general item
   * @param initReleaseYear
   *          of the general item
   * @param initPublisher
   *          of the general item
   * @param initAuthor
   *          author in the case of book, director in the case of movie
   * @param initCurrentLanguage
   *          of the general item contained in the archive
   * @param initGenre
   *          of the general item
   *
   */
  public ItemImpl(final String initTitle, final int initReleaseYear, final String initPublisher,
              final String initAuthor, final Language initCurrentLanguage,
              final ItemGenre initGenre) {
    this.title = initTitle.toUpperCase();
    this.releaseYear = initReleaseYear;
    this.publisher = initPublisher.toUpperCase();
    this.author = initAuthor.toUpperCase();
    this.currentLanguage = initCurrentLanguage;
    this.like = new HashSet<Integer>();
    this.averageVote = 0;
    this.iD = this.hashCode();
    this.genre = initGenre;
  }

  /**
   * Item's constructors whit starter initialization starting from an ItemImpl,
   * it's used in the method where I have to return a copy of an Item.
   *
   * @param initItem
   *          item.
   */
  public ItemImpl(final Item initItem) {
    this.title = initItem.getTitle();
    this.releaseYear = initItem.getReleaseYear();
    this.publisher = initItem.getPublisher();
    this.author = initItem.getAuthor();
    this.currentLanguage = initItem.getCurrentLanguage();
    this.like = initItem.getLikeUser();
    this.averageVote = initItem.getAverageVote();
    this.iD = initItem.getiD();
    this.genre = initItem.getGenre();
  }

  @Override
  public String toString() {
    return "[iD=" + this.iD + ", title=" + this.title + ", releaseYear=" + this.releaseYear
                + ", publisher=" + this.publisher + ", author=" + this.author + ", currentLanguage="
                + this.currentLanguage + ", setReview=" + this.setReview.toString() + ", like="
                + this.like + ", averageVote=" + this.averageVote + this.genre;
  }

  @Override
  public String getPublisher() {
    String s = this.publisher;
    return s;
  }

  @Override
  public Language getCurrentLanguage() {
    Language l = this.currentLanguage;
    return l;
  }

  @Override
  public int getiD() {
    int i = this.iD;
    return i;
  }

  @Override
  public String getTitle() {
    String s = this.title;
    return s;
  }

  @Override
  public int getReleaseYear() {
    int i = this.releaseYear;
    return i;
  }

  @Override
  public String getAuthor() {
    String s = this.author;
    return s;
  }

  @Override
  public List<ReviewImpl> getSetReview() {
    List<ReviewImpl> l = new LinkedList<>(this.setReview);
    return l;
  }

  @Override
  public int getLike() {
    int i = this.like.size();
    return i;
  }

  @Override
  public Set<Integer> getLikeUser() {
    Set<Integer> s = new HashSet<>(this.like);
    return s;
  }

  @Override
  public float getAverageVote() {
    float f = this.averageVote;
    return f;
  }

  @Override
  public ItemGenre getGenre() {
    ItemGenre g = this.genre;
    return g;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.author, this.releaseYear, this.title, this.publisher,
                this.currentLanguage);
  }

  @Override
  public boolean equals(final Object object) {
    if (!(object instanceof ItemImpl)) {
      return false;
    }
    final ItemImpl item = (ItemImpl) object;
    return Objects.equal(this.author, item.author)
                && Objects.equal(this.releaseYear, item.releaseYear)
                && Objects.equal(this.title, item.title)
                && Objects.equal(this.publisher, item.publisher)
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
