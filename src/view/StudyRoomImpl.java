package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import view.ViewImpl.CardName;

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
	private int selectedSit;
	private final JButton[] buttons;
	private JDatePickerImpl datePicker;
	private UtilDateModel model = new UtilDateModel();
	private SpringLayout springLayout = new SpringLayout();
	private JPanel centerPanel;

	/**
	 * builder for StudyRoomImpl.
	 *
	 * @param v
	 *            the calling class
	 */
	public StudyRoomImpl(final View v) {
		JPanel southPanel = new JPanel();
		southPanel.setBounds(0, 465, 800, 135);
		JPanel northPanel = new JPanel();
		northPanel.setBounds(0, 0, 800, 135);

		this.centerPanel = new JPanel();
		this.centerPanel.setBounds(0, 134, 800, 331);
		this.add(this.centerPanel);

		this.setSize(ViewImpl.SCREEN_LENGHT, ViewImpl.SCREEN_WIDTH);
		this.buttons = new JButton[ViewImpl.STUDY_ROOM_SITS];

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		this.model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(this.model, p);
		this.datePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		this.springLayout.putConstraint(SpringLayout.NORTH,
				this.datePicker.getJFormattedTextField(), 0,
				SpringLayout.NORTH, this.datePicker);
		this.springLayout = (SpringLayout) this.datePicker.getLayout();
		this.springLayout.putConstraint(SpringLayout.SOUTH,
				this.datePicker.getJFormattedTextField(), 0,
				SpringLayout.SOUTH, this.datePicker);
		this.datePicker.setSize(241, 25);
		this.datePicker.setLocation(158, 86);

		northPanel.add(this.datePicker);

		southPanel.setLayout(null);

		int i;
		for (i = 0; i < 100; i++) {
			this.buttons[i] = new JButton();
			this.buttons[i].setSize(80, 80);
			this.buttons[i].setBackground(Color.GREEN);
			this.centerPanel.add(this.buttons[i]);

		}
		v.giveMeStudyRoomStatus();
		for (i = 0; i < 100; i++) {
			this.buttons[i] = new JButton();
			this.buttons[i].setText(String.valueOf(i));
			if (this.buttons[i].getBackground() == Color.GREEN) {
				this.buttons[i].addActionListener(e -> v.takeSit());
				this.selectedSit = Integer.parseInt(this.buttons[i].getText());
			} else if (this.buttons[i].getBackground() == Color.CYAN) {
				this.buttons[i].addActionListener(e -> v.cancelSit());
				this.selectedSit = Integer.parseInt(this.buttons[i].getText());
			} else {
				this.buttons[i].setEnabled(false);
			}
		}

		new BorderLayout();
		this.setLayout(null);
		this.add(southPanel);

		JButton exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(629, 24, 128, 49);
		exit.addActionListener(e -> {
			v.giveMeSuggestedBooks();
			v.giveMeSuggestedMovies();
			v.swapView(CardName.MENU);
		});
		southPanel.add(exit);

		this.add(northPanel);
		northPanel.setLayout(null);
		this.add(this.centerPanel);
		this.centerPanel.setLayout(new FlowLayout());
		northPanel.setLayout(null);
		JLabel presentation = new JLabel(
				"Clicca una data e il posto che vuoi prenotare");
		presentation.setHorizontalAlignment(SwingConstants.CENTER);
		presentation
				.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		presentation.setBounds(12, 13, 776, 71);
		northPanel.add(presentation);

		JButton sendDate = new JButton("Invia Data");
		sendDate.addActionListener(e -> {
			v.giveMeStudyRoomStatus();
			v.swapView(CardName.STUDY_ROOM);
		});
		sendDate.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		sendDate.setBounds(427, 82, 158, 40);
		northPanel.add(sendDate);

	}

	@Override
	public int getTakenSit() {
		return this.selectedSit;
	}

	@Override
	public void setStudyRoomStatus(final String[] status) {
		int i;
		for (i = 0; i < status.length; i++) {
			this.centerPanel.add(this.buttons[i]);
			this.buttons[i].setText(String.valueOf(i));
			if (status[i].equals("0")) {
				this.buttons[i].setBackground(Color.GREEN);
			} else if (status[i].equals("1")) {
				this.buttons[i].setBackground(Color.CYAN);
			} else {
				this.buttons[i].setBackground(Color.RED);
				this.buttons[i].setEnabled(false);
			}
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
}
