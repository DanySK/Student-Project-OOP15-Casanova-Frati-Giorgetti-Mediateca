package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	int takenSit;
	JButton[] buttons;

	public StudyRoomImpl(final View v, final int sitsNumber) {
		this.setSize(1280, 920);
		JLabel presentation = new JLabel("Clicca sul posto che vuoi prenotare");
		presentation.setFont(new Font("Tahoma", Font.BOLD, 40));
		this.buttons = new JButton[sitsNumber];
		int i;

		ActionListener listener = e -> {
			if (e.getSource() instanceof JButton) {
				int sit = Integer.parseInt(((JButton) e.getSource()).getText());
				v.takeSit();
				StudyRoomImpl.this.takenSit = sit;
				v.giveMeStudyRoomStatus();
			}
		};

		for (i = 0; i < sitsNumber; i++) {
			this.buttons[i] = new JButton(String.valueOf(i));
			this.buttons[i].addActionListener(listener);
			this.buttons[i].setBackground(Color.GREEN);
			this.add(this.buttons[i]);
		}

		this.add(presentation);
		v.giveMeStudyRoomStatus();
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
}
