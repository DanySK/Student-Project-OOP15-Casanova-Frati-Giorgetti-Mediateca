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

class StudyRoomImpl extends JPanel implements StudyRoom {

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
		JButton sendDate = new JButton("Invia Data");

		sendDate.addActionListener(e -> {
			v.giveMeStudyRoomStatus();
			System.out.println("Invia data cliccato");
			v.swapView(CardName.STUDY_ROOM);
		});

		int i;
		for (i = 0; i < 100; i++) {
			this.buttons[i] = new JButton();
			this.buttons[i].setSize(30, 30);
			this.buttons[i].setText(String.valueOf(i + 1));
			this.centerPanel.add(this.buttons[i]);

		}

		for (i = 0; i < 100; i++) {
			this.buttons[i]
					.addActionListener(e -> {
						if (((JButton) e.getSource()).getBackground() == Color.CYAN) {
							this.selectedSit = Integer.parseInt(((JButton) e
									.getSource()).getText());
							v.takeSit();
							System.out.println("preso posto"
									+ String.valueOf(this.selectedSit));
							v.giveMeStudyRoomStatus();
							v.swapView(CardName.STUDY_ROOM);
						} else if (((JButton) e.getSource()).getBackground() == Color.GREEN) {
							this.selectedSit = Integer.parseInt(((JButton) e
									.getSource()).getText());
							v.cancelSit();
							System.out.println("cancellato posto"
									+ String.valueOf(this.selectedSit));
							v.giveMeStudyRoomStatus();
							v.swapView(CardName.STUDY_ROOM);
						}
					});
		}

		new BorderLayout();
		this.setLayout(null);
		this.add(southPanel);

		JButton exit = new JButton("Esci");
		exit.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		exit.setBounds(567, 19, 128, 49);
		exit.addActionListener(e -> {
			v.giveMeSuggestedBooks();
			v.giveMeSuggestedMovies();
			v.swapView(CardName.MENU);
		});
		southPanel.add(exit);

		JLabel freeSit = new JLabel("Posto libero, clicca per prenderlo");
		freeSit.setHorizontalAlignment(SwingConstants.CENTER);
		freeSit.setOpaque(true);
		freeSit.setBackground(Color.WHITE);
		freeSit.setForeground(Color.GREEN);
		freeSit.setFont(new Font("Tahoma", Font.BOLD, 20));
		freeSit.setBounds(28, 0, 430, 30);
		southPanel.add(freeSit);

		JLabel justTakenSit = new JLabel(
				"Posto gi\u00E0 prenotato, clicca per liberarlo");
		justTakenSit.setHorizontalAlignment(SwingConstants.CENTER);
		justTakenSit.setOpaque(true);
		justTakenSit.setBackground(Color.WHITE);
		justTakenSit.setForeground(Color.CYAN);
		justTakenSit.setFont(new Font("Tahoma", Font.BOLD, 20));
		justTakenSit.setBounds(28, 30, 430, 30);
		southPanel.add(justTakenSit);

		JLabel occupiedSit = new JLabel("Posto occupato da un altro utente\r\n");
		occupiedSit.setHorizontalAlignment(SwingConstants.CENTER);
		occupiedSit.setOpaque(true);
		occupiedSit.setBackground(Color.WHITE);
		occupiedSit.setForeground(Color.RED);
		occupiedSit.setFont(new Font("Tahoma", Font.BOLD, 20));
		occupiedSit.setBounds(28, 60, 430, 30);
		southPanel.add(occupiedSit);

		this.add(northPanel);
		northPanel.setLayout(null);
		this.add(this.centerPanel);
		this.centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		northPanel.setLayout(null);
		JLabel presentation = new JLabel(
				"Clicca una data e il posto che vuoi prenotare");
		presentation.setHorizontalAlignment(SwingConstants.CENTER);
		presentation
		.setFont(new Font("Tahoma", Font.PLAIN, ViewImpl.FONT_SIZE));
		presentation.setBounds(12, 13, 776, 71);
		northPanel.add(presentation);

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
			if (status[i].equals("0")) {
				this.buttons[i].setBackground(Color.GREEN);
				this.buttons[i].setEnabled(true);
			} else if (status[i].equals("1")) {
				this.buttons[i].setBackground(Color.CYAN);
				this.buttons[i].setEnabled(true);
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
