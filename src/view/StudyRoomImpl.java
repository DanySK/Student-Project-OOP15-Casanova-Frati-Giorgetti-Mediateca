package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 * Class which implements methods of StudyRoom interface.
 *
 * @author Luca Giorgetti
 *
 */
public class StudyRoomImpl extends JPanel implements StudyRoom {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int takenSit;
	private final JButton[] buttons;
	private JDatePickerImpl datePicker;
	private UtilDateModel model = new UtilDateModel();
	JList<String> takenSitsList = new JList<String>();
	private SpringLayout springLayout = new SpringLayout();

	/**
	 * builder for StudyRoomImpl.
	 *
	 * @param v
	 * @param sitsNumber
	 */
	public StudyRoomImpl(final View v) {
		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		JLabel presentation = new JLabel("Clicca sul posto che vuoi prenotare");
		presentation.setHorizontalAlignment(SwingConstants.CENTER);
		presentation.setBounds(12, 13, 776, 49);
		presentation
		.setFont(new Font("Tahoma", Font.BOLD, ViewImpl.TITLE_SIZE));
		this.buttons = new JButton[ViewImpl.STUDY_ROOM_SITS];
		int i;

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		this.model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(this.model, p);
		this.setLayout(null);
		this.datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		this.springLayout.putConstraint(SpringLayout.NORTH,
				this.datePicker.getJFormattedTextField(), 0,
				SpringLayout.NORTH, this.datePicker);
		this.springLayout.putConstraint(SpringLayout.SOUTH,
				this.datePicker.getJFormattedTextField(), -15,
				SpringLayout.SOUTH, this.datePicker);
		this.springLayout.putConstraint(SpringLayout.EAST,
				this.datePicker.getJFormattedTextField(), -27,
				SpringLayout.EAST, this.datePicker);
		this.springLayout = (SpringLayout) this.datePicker.getLayout();
		this.springLayout.putConstraint(SpringLayout.WEST,
				this.datePicker.getJFormattedTextField(), 0, SpringLayout.WEST,
				this.datePicker);
		this.datePicker.setSize(229, 25);
		this.datePicker.setLocation(530, 67);
		this.takenSitsList.setSize(620, 161);
		this.takenSitsList.setLocation(12, 415);
		this.add(this.takenSitsList);
		this.add(this.datePicker);

		final ActionListener listener = e -> {
			if (e.getSource() instanceof JButton) {
				int sit = Integer.parseInt(((JButton) e.getSource()).getText());
				v.takeSit();
				StudyRoomImpl.this.takenSit = sit;
				v.giveMeStudyRoomStatus();
			}
		};

		for (i = 0; i < 50; i++) {
			this.buttons[i] = new JButton(String.valueOf(i));
			this.buttons[i].addActionListener(listener);
			this.buttons[i].setBackground(Color.GREEN);
			this.add(this.buttons[i]);
		}

		this.add(presentation);
		v.giveMeStudyRoomStatus();
		JButton remove = new JButton("Rimuovi");
		remove.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		remove.setBounds(644, 471, 127, 55);
		this.add(remove);

		JLabel instruction = new JLabel(
				"Inserisci la data in cui vuoi prendere posto:");
		instruction.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		instruction.setBounds(22, 67, 603, 25);
		this.add(instruction);
		remove.addActionListener(e -> v.removeSit());

	}

	@Override
	public int getTakenSit() {
		return this.takenSit;
	}

	@Override
	public void setStudyRoomStatus(final int[] status) {
		int i;
		for (i = 0; i < status.length; i++) {
			this.buttons[status[i]].setBackground(Color.RED);
		}
	}

	@Override
	public int getDateDay() {
		return this.datePicker.getModel().getDay();
	}

	@Override
	public int getDateMonth() {
		return this.datePicker.getModel().getMonth();
	}

	@Override
	public int getDateYear() {
		return this.datePicker.getModel().getYear();
	}

	@Override
	public void setTakenSitsList(final String[] list) {
		this.takenSitsList = new JList<String>(list);
	}

	@Override
	public String getSelectedSit() {
		return this.takenSitsList.getSelectedValue();
	}
}
