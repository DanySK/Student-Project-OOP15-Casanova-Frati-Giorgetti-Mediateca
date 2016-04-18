package model;
import java.awt.Image;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

public class Item_impl implements Item{

	String title;
	int release_year;
	String author;
	Optional<Image> cover;
	Optional<List<Review>> set_review;
	int like;
	
	/**
	 * hashCode function uses the Objects.hashCode( field1, field2, .. ) 
	 * taken from Google Guava
	 */
	@Override
	public int hashCode(){
		return Objects.hashCode(author, release_year, title);
	}
	
	/**
	 * equals function uses the Objects.equal(...)
	 * taken from Google Guava
	 */
	@Override
	public boolean equals(Object object) {
	      if(!(object instanceof Item_impl) || object == null){
	          return false;
	       }
	      Item_impl item = (Item_impl)object;
	       return Objects.equal(author, item.author)
	    	&& Objects.equal(release_year, item.release_year)
	        && Objects.equal(title, item.title);
	    }
}
