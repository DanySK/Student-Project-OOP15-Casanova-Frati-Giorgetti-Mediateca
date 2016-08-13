package view;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import view.ViewImpl.CardName;

/**
 * Class which implements the ReviewScreen interface.
 *
 * @author Luca Giorgetti
 *
 */
public class ReviewScreenImpl extends JPanel implements ReviewScreen {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static final int FRAME_LENGHT = 1280;
	static final int FRAME_WIDTH = 920;
	static final int MAX_MARK = 5;
	private final JTextArea review;
	private static int score;
	private JButton discard;
	private JButton send;
	private ButtonGroup scoreGroup = new ButtonGroup();

	/**
	 * Builder for ReviewScreen.
	 *
	 * @param v
	 * @param sreenWidth
	 * @param screenLenght
	 */
	public ReviewScreenImpl(final View v, final int screenLenght,
			final int sreenWidth) {

		final JLabel insertReview = new JLabel(
				"Inserisci qui una recensione per l'oggetto selezionato:");
		insertReview.setBounds(62, 95, 308, 16);
		final JLabel insertMark = new JLabel("Inserisci qui un voto (da 1 a "
				+ ReviewScreenImpl.MAX_MARK + ") per l'oggetto selezionato:");
		insertMark.setBounds(43, 36, 324, 16);
		this.setLayout(null);

		this.add(insertReview);
		this.add(insertMark);

		this.scoreGroup = new ButtonGroup();
		JRadioButton score1 = new JRadioButton("1");
		score1.setBounds(97, 61, 35, 25);
		this.scoreGroup.add(score1);
		this.add(score1);

		JRadioButton score2 = new JRadioButton("2");
		score2.setBounds(136, 61, 35, 25);
		this.scoreGroup.add(score2);
		this.add(score2);

		JRadioButton score3 = new JRadioButton("3");
		score3.setBounds(175, 61, 35, 25);
		this.scoreGroup.add(score3);
		this.add(score3);

		JRadioButton score4 = new JRadioButton("4");
		score4.setBounds(214, 61, 35, 25);
		this.scoreGroup.add(score4);
		this.add(score4);

		JRadioButton score5 = new JRadioButton("5");
		score5.setBounds(253, 61, 35, 25);
		this.scoreGroup.add(score5);
		this.add(score5);

		this.discard = new JButton("Annulla");
		this.discard.setBounds(12, 262, 75, 25);
		this.add(this.discard);

		this.send = new JButton("Invia Recensione");
		this.send.setBounds(309, 262, 129, 25);
		this.add(this.send);

		this.review = new JTextArea();
		this.review.setBounds(59, 124, 324, 100);
		this.add(this.review);
		this.review.setLineWrap(true);
		this.review.setToolTipText("Inserisci qui una recensione");
		this.review.setColumns(10);
		this.review.setWrapStyleWord(true);
		this.review.setLineWrap(true);
		this.add(this.review);
		this.discard.addActionListener(e -> {
			v.swapView(CardName.ITEM);
		});

		this.send.addActionListener(e -> {
			v.controllerGetReview();

		});
	}

	@Override
	public int getSelectedScore() {
		for (Enumeration<AbstractButton> scores = this.scoreGroup.getElements(); scores
				.hasMoreElements();) {
			AbstractButton score = scores.nextElement();

			if (score.isSelected()) {
				ReviewScreenImpl.score = Integer.parseInt(score.getText());
			}
		}
		return ReviewScreenImpl.score;
	}

	@Override
	public String getReview() {
		return this.review.getText();
	}
}
