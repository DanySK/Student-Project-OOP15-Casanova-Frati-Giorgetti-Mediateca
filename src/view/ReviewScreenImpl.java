package view;

import java.awt.BorderLayout;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ReviewScreenImpl implements ReviewScreen {
	final static int FRAME_LENGHT = 1280;
	final static int FRAME_WIDTH = 920;
	final static int MAX_MARK = 5;
	private JTextArea review;
	private JButton discard;
	private JButton send;
	private ButtonGroup scoreGroup = new ButtonGroup();
	private int scoreTyped;
	private String reviewTyped;
	private View toViewClass = new ViewImpl();

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startReviewScreen() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("Recensione");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ReviewScreenImpl.FRAME_LENGHT,
				ReviewScreenImpl.FRAME_WIDTH);
		mainFrame.setResizable(false);
		final JPanel mainPanel = new JPanel();
		final JLabel insertReview = new JLabel(
				"Inserisci qui una recensione per l'oggetto selezionato:");
		insertReview.setBounds(376, 151, 308, 16);
		final JLabel insertMark = new JLabel("Inserisci qui un voto (da 1 a "
				+ ReviewScreenImpl.MAX_MARK + ") per l'oggetto selezionato:");
		insertMark.setBounds(376, 104, 332, 16);
		mainPanel.setLayout(null);

		mainPanel.add(insertReview);
		mainPanel.add(insertMark);

		this.scoreGroup = new ButtonGroup();
		JRadioButton score1 = new JRadioButton("1");
		score1.setBounds(716, 100, 43, 25);
		this.scoreGroup.add(score1);
		mainPanel.add(score1);

		JRadioButton score2 = new JRadioButton("2");
		score2.setBounds(763, 100, 43, 25);
		this.scoreGroup.add(score2);
		mainPanel.add(score2);

		JRadioButton score3 = new JRadioButton("3");
		score3.setBounds(805, 100, 43, 25);
		this.scoreGroup.add(score3);
		mainPanel.add(score3);

		JRadioButton score4 = new JRadioButton("4");
		score4.setBounds(846, 100, 43, 25);
		this.scoreGroup.add(score4);
		mainPanel.add(score4);

		JRadioButton score5 = new JRadioButton("5");
		score5.setBounds(893, 100, 43, 25);
		this.scoreGroup.add(score5);
		mainPanel.add(score5);

		this.review = new JTextArea();
		this.review.setLineWrap(true);
		this.review.setToolTipText("Inserisci qui una recensione");
		this.review.setBounds(376, 196, 560, 415);
		this.review.setColumns(10);
		this.review.setWrapStyleWord(true);
		this.review.setLineWrap(true);
		mainPanel.add(this.review);
		this.discard = new JButton("Annulla");
		this.discard.setBounds(376, 624, 97, 25);
		mainPanel.add(this.discard);

		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);

		this.send = new JButton("Invia Recensione");
		this.send.setBounds(805, 624, 131, 25);
		mainPanel.add(this.send);

		mainFrame.setVisible(true);
		this.discard.addActionListener(e -> {
			mainFrame.setVisible(false);
			mainFrame.dispose();
		});

		this.send.addActionListener(e -> {
			this.toViewClass.getReview();
			mainFrame.dispose();

		});
	}

	@Override
	public int getSelectedScore() {
		for (Enumeration<AbstractButton> scores = this.scoreGroup.getElements(); scores
				.hasMoreElements();) {
			AbstractButton score = scores.nextElement();

			if (score.isSelected()) {
				return Integer.parseInt(score.getText());
			}
		}
		return 0;
	}

	@Override
	public String getReview() {
		return this.review.getText();
	}
}
