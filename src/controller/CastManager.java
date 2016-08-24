package controller;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;

public class CastManager {
	public CastManager() {

	}

	/**
	 * Method which casts the given string to an Itemgenre type
	 * 
	 * @param string
	 * @return
	 */
	public static ItemGenre castToItemGenre(final String string) {

		for (ItemGenre ig : ItemGenre.values()) {
			if (ig.toString().equals(string)) {
				return ig;
			}
		}
		return null;
	}

	/**
	 * Method which casts the given string to a Language type
	 * 
	 * @param string
	 * @return
	 */
	public static Language castToLanguage(final String string) {

		for (Language l : Language.values()) {
			if (l.toString().equals(string)) {
				return l;
			}
		}
		return null;
	}

	public static TypeColor castToTypeColor(final String string) {
		for (TypeColor tc : TypeColor.values()) {
			if (tc.toString().equals(string)) {
				return tc;
			}
		}
		return null;
	}
}
