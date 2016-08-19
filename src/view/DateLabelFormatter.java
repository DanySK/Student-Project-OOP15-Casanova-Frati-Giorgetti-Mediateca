package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Class which set the date formattation.
 *
 * @author Luca Giorgetti
 *
 */
public class DateLabelFormatter extends AbstractFormatter {

	/**
	 *
	 */
	private static final long serialVersionUID = -8832972662884810321L;
	final String datePattern = "dd-MM-yyyy";
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			this.datePattern);

	@Override
	public Object stringToValue(final String text) throws ParseException {
		return this.dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(final Object value) throws ParseException {
		if (value != null) {
			final Calendar cal = (Calendar) value;
			return this.dateFormatter.format(cal.getTime());
		}

		return "";
	}

}