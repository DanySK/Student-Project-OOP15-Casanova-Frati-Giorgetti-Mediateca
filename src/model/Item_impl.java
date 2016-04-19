package model;

import java.awt.Image;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

public class Item_impl implements Item {

	private String title;
	private int release_year;
	private String author;
	private Optional<Image> cover;
	private Optional<List<Review>> set_review;
	private int like;

	/**
	 * hashCode function uses the Objects.hashCode( field1, field2, .. ) taken
	 * from Google Guava
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(this.author, this.release_year, this.title);
	}

	/**
	 * equals function uses the Objects.equal(...) taken from Google Guava
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof Item_impl) || (object == null)) {
			return false;
		}
		final Item_impl item = (Item_impl) object;
		return Objects.equal(this.author, item.author) && Objects.equal(this.release_year, item.release_year)
				&& Objects.equal(this.title, item.title);
	}
}
