package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BorrowedScreenImpl implements BorrowedScreen {
	final static int FRAME_LENGHT = 1280;
	final static int FRAME_WIDTH = 920;

	private View toViewClass = new ViewImpl();
	private JLabel presentation;
	private JList<String> list;
	private JButton exit;

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void startBorrowedScreen() {
		final JFrame mainFrame = new JFrame();
		JPanel mainPanel = new JPanel();

		mainFrame.setTitle("Oggetti in prestito");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(ReviewScreenImpl.FRAME_LENGHT,
				ReviewScreenImpl.FRAME_WIDTH);
		mainFrame.setResizable(false);

		this.presentation = new JLabel("Ecco gli oggetti che hai in prestito:");
		this.presentation.setBounds(525, 33, 197, 16);
		mainPanel.setLayout(null);
		mainPanel.add(this.presentation);

		mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.list.setBounds(51, 41, 349, 228);
		mainPanel.add(this.list);

		this.list = new JList<String>();
		this.list.setBounds(129, 62, 1004, 694);
		mainPanel.add(this.list);

		this.exit = new JButton("Esci");
		this.exit.setBounds(921, 778, 212, 94);
		mainPanel.add(this.exit);
		this.exit.addActionListener(e -> {
			mainFrame.setVisible(false);
			mainFrame.dispose();
		});

	}

	public List<String> setBorrowedList() {
		return this.toViewClass.setBorrowedItemList();
	}
}