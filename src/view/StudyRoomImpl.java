package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

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

	/**
	 * builder for StudyRoomImpl.
	 *
	 * @param v
	 * @param sitsNumber
	 */
	public StudyRoomImpl(final View v, final int sitsNumber) {
		this.setSize(1280, 920);
		JLabel presentation = new JLabel("Clicca sul posto che vuoi prenotare");
		presentation.setBounds(311, 13, 705, 49);
		presentation.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.buttons = new JButton[sitsNumber];
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
		this.datePicker.setSize(202, 25);
		this.datePicker.setLocation(979, 107);
		this.takenSitsList.setSize(826, 89);
		this.takenSitsList.setLocation(56, 740);
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
		remove.setFont(new Font("Tahoma", Font.PLAIN, 30));
		remove.setBounds(920, 737, 229, 92);
		this.add(remove);
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
	public String getDate() {
		return (String) this.datePicker.getModel().getValue();
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
