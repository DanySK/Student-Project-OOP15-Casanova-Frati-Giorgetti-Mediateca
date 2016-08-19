package view;

import java.awt.Font;
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
		insertReview.setBounds(43, 246, 1208, 45);
		insertReview.setFont(new Font("Tahoma", Font.PLAIN, 30));
		final JLabel insertMark = new JLabel("Inserisci qui un voto (da 1 a "
				+ ReviewScreenImpl.MAX_MARK + ") per l'oggetto selezionato:");
		insertMark.setBounds(43, 84, 1208, 45);
		insertMark.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.setLayout(null);
		this.setSize(1280, 920);

		this.add(insertReview);
		this.add(insertMark);

		this.scoreGroup = new ButtonGroup();
		JRadioButton score1 = new JRadioButton("1");
		score1.setBounds(43, 138, 58, 45);
		score1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.scoreGroup.add(score1);
		this.add(score1);

		JRadioButton score2 = new JRadioButton("2");
		score2.setBounds(105, 138, 58, 45);
		this.scoreGroup.add(score2);
		score2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(score2);

		JRadioButton score3 = new JRadioButton("3");
		score3.setBounds(167, 138, 58, 45);
		score3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.scoreGroup.add(score3);
		this.add(score3);

		JRadioButton score4 = new JRadioButton("4");
		score4.setBounds(229, 138, 58, 45);
		score4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.scoreGroup.add(score4);
		this.add(score4);

		JRadioButton score5 = new JRadioButton("5");
		score5.setBounds(291, 138, 58, 45);
		score5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.scoreGroup.add(score5);
		this.add(score5);

		this.discard = new JButton("Annulla");
		this.discard.setBounds(43, 821, 287, 75);
		this.discard.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(this.discard);

		this.send = new JButton("Invia Recensione");
		this.send.setBounds(935, 821, 316, 75);
		this.send.setFont(new Font("Tahoma", Font.PLAIN, 30));
		this.add(this.send);

		this.review = new JTextArea();
		this.review.setBounds(46, 304, 1205, 382);
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
